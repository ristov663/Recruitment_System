package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.interview.*
import com.bojan.recruitment.exceptions.EntityNotFoundException
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.model.Interview
import com.bojan.recruitment.repository.ApplicationRepository
import com.bojan.recruitment.repository.InterviewRepository
import com.bojan.recruitment.service.EmailService
import com.bojan.recruitment.service.InterviewService
import org.springframework.stereotype.Service
import java.util.*

@Service
class InterviewServiceImpl(
    private val interviewRepository: InterviewRepository,
    private val applicationRepository: ApplicationRepository,
    private val emailService: EmailService
) : InterviewService {

    override fun schedule(dto: InterviewRequestDTO): InterviewResponseDTO {

        val application = applicationRepository.findById(dto.applicationId)
            .orElseThrow { EntityNotFoundException("Application not found") }

        val interview = Interview(
            application = application,
            scheduledAt = dto.scheduledAt,
            interviewer = dto.interviewer
        )

        val candidateEmail = application.candidate.email

        emailService.sendInterviewScheduledEmail(
            candidateEmail,
            interview.scheduledAt.toString(),
            interview.notes ?: ""
        )

        return interviewRepository.save(interview).toDto()
    }

    override fun scoreInterview(id: UUID, dto: InterviewScoreDTO): InterviewResponseDTO {

        val interview = interviewRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Interview not found") }

        interview.score = dto.score
        interview.notes = dto.notes

        return interviewRepository.save(interview).toDto()
    }

    override fun getByApplication(applicationId: UUID): List<InterviewResponseDTO> {
        return interviewRepository.findByApplicationId(applicationId).map { it.toDto() }
    }
}
