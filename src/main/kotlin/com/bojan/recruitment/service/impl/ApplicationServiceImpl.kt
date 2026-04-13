package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.application.ApplicationRequestDTO
import com.bojan.recruitment.dto.application.ApplicationResponseDTO
import com.bojan.recruitment.dto.application.ApplicationStatusUpdateDTO
import com.bojan.recruitment.dto.application.MatchExplanationDTO
import com.bojan.recruitment.enums.ApplicationStatus
import com.bojan.recruitment.exceptions.EntityNotFoundException
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.mapper.toPageDto
import com.bojan.recruitment.model.Application
import com.bojan.recruitment.repository.ApplicationRepository
import com.bojan.recruitment.repository.JobRepository
import com.bojan.recruitment.repository.UserRepository
import com.bojan.recruitment.service.ApplicationService
import com.bojan.recruitment.service.CvParsingService
import com.bojan.recruitment.service.CvSummaryService
import com.bojan.recruitment.service.ai.AiExplanationService
import com.bojan.recruitment.service.ai.AiMatchingService
import com.bojan.recruitment.util.getCurrentUserId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ApplicationServiceImpl(
    private val applicationRepository: ApplicationRepository,
    private val jobRepository: JobRepository,
    private val userRepository: UserRepository,
    private val aiMatchingService: AiMatchingService,
    private val aiExplanationService: AiExplanationService,
    private val cvParsingService: CvParsingService,
    private val cvSummaryService: CvSummaryService
) : ApplicationService {

    override fun applyForJob(dto: ApplicationRequestDTO): ApplicationResponseDTO {

        val candidateId = getCurrentUserId()

        val candidate = userRepository.findById(candidateId)
            .orElseThrow {
                EntityNotFoundException("User with ID $candidateId not found.")
            }

        val job = jobRepository.findById(dto.jobId)
            .orElseThrow {
                EntityNotFoundException("Job with ID ${dto.jobId} not found.")
            }

        val file = java.io.File(dto.cvUrl)

        val cvText = cvParsingService.extractText(file)

        val score = aiMatchingService.calculateScore(
            cvText,
            job.description ?: ""
        )

        val application = Application(
            candidate = candidate,
            job = job,
            cvUrl = dto.cvUrl,
            status = ApplicationStatus.APPLIED,
            matchScore = score
        )

        return applicationRepository.save(application).toDto()
    }

    @Transactional
    override fun updateStatus(id: UUID, dto: ApplicationStatusUpdateDTO): ApplicationResponseDTO {

        val application = applicationRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("Application with ID $id not found.")
            }

        application.updateStatus(dto.status)

        return applicationRepository.save(application).toDto()
    }

    override fun getApplication(id: UUID): ApplicationResponseDTO {

        return applicationRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("Application with ID $id not found.")
            }
            .toDto()
    }

    override fun getApplicationsByJob(
        jobId: UUID,
        pageable: Pageable
    ): PageResponse<ApplicationResponseDTO> {

        return applicationRepository.findByJobId(jobId, pageable)
            .map { it.toDto() }
            .toPageDto()
    }

    override fun getMyApplications(pageable: Pageable): PageResponse<ApplicationResponseDTO> {

        val userId = getCurrentUserId()

        return applicationRepository.findByCandidateId(userId, pageable)
            .map { it.toDto() }
            .toPageDto()
    }

    override fun explainApplication(id: UUID): MatchExplanationDTO {

        val application = applicationRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Application not found.") }

        val file = java.io.File(application.cvUrl)

        val cvText = cvParsingService.extractText(file)

        val explanation = aiExplanationService.explain(
            cvText,
            application.job.description ?: "",
            application.matchScore ?: 0.0
        )

        return MatchExplanationDTO(
            score = application.matchScore ?: 0.0,
            explanation = explanation
        )
    }

    override fun getBestCandidate(jobId: UUID): ApplicationResponseDTO {

        val application = applicationRepository
            .findByJobIdOrderByMatchScoreDesc(jobId, Pageable.ofSize(1))
            .content
            .firstOrNull()
            ?: throw EntityNotFoundException("No applications found.")

        return application.toDto()
    }

    override fun getApplicationSummary(id: UUID): String {

        val application = applicationRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Application not found.") }

        val file = java.io.File(application.cvUrl)

        val cvText = cvParsingService.extractText(file)

        return cvSummaryService.summarize(cvText)
    }
}
