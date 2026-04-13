package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.job.JobRequestDTO
import com.bojan.recruitment.dto.job.JobResponseDTO
import com.bojan.recruitment.dto.job.JobUpdateDTO
import com.bojan.recruitment.exceptions.EntityNotFoundException
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.mapper.toJobEntity
import com.bojan.recruitment.mapper.toPageDto
import com.bojan.recruitment.repository.JobRepository
import com.bojan.recruitment.service.JobService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.core.context.SecurityContextHolder
import com.bojan.recruitment.model.User
import java.util.UUID

@Service
class JobServiceImpl(
    private val jobRepository: JobRepository
) : JobService {

    override fun getAllJobs(pageable: Pageable): PageResponse<JobResponseDTO> {
        return jobRepository.findAll(pageable)
            .map { it.toDto() }
            .toPageDto()
    }

    override fun getJobById(id: UUID): JobResponseDTO {
        return jobRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("Job with ID $id not found.")
            }
            .toDto()
    }

    override fun createJob(dto: JobRequestDTO): JobResponseDTO {

        val principal = SecurityContextHolder.getContext().authentication?.principal

        val user = principal as? User
            ?: throw RuntimeException("User not authenticated")

        val entity = dto.toJobEntity(user)

        return jobRepository.save(entity).toDto()
    }

    @Transactional
    override fun updateJob(id: UUID, dto: JobUpdateDTO): JobResponseDTO {

        val job = jobRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("Job with ID $id not found.")
            }

        job.update(dto)

        return jobRepository.save(job).toDto()
    }

    override fun deleteJob(id: UUID) {

        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id)
        }
    }

    override fun searchJobs(query: String, pageable: Pageable): PageResponse<JobResponseDTO> {
        return jobRepository.searchJobs(query, pageable)
            .map { it.toDto() }
            .toPageDto()
    }
}
