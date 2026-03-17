package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.application.ApplicationRequestDTO
import com.bojan.recruitment.dto.application.ApplicationResponseDTO
import com.bojan.recruitment.dto.application.ApplicationStatusUpdateDTO
import com.bojan.recruitment.enums.ApplicationStatus
import com.bojan.recruitment.exceptions.EntityNotFoundException
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.mapper.toPageDto
import com.bojan.recruitment.model.Application
import com.bojan.recruitment.repository.ApplicationRepository
import com.bojan.recruitment.repository.JobRepository
import com.bojan.recruitment.repository.UserRepository
import com.bojan.recruitment.service.ApplicationService
import com.bojan.recruitment.util.getCurrentUserId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ApplicationServiceImpl(
    private val applicationRepository: ApplicationRepository,
    private val jobRepository: JobRepository,
    private val userRepository: UserRepository
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

        val application = Application(
            candidate = candidate,
            job = job,
            cvUrl = dto.cvUrl,
            status = ApplicationStatus.APPLIED
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
}
