package com.bojan.recruitment.service

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.job.JobRequestDTO
import com.bojan.recruitment.dto.job.JobResponseDTO
import com.bojan.recruitment.dto.job.JobUpdateDTO
import org.springframework.data.domain.Pageable
import java.util.UUID

interface JobService {

    fun getAllJobs(pageable: Pageable): PageResponse<JobResponseDTO>
    fun getJobById(id: UUID): JobResponseDTO
    fun createJob(dto: JobRequestDTO): JobResponseDTO
    fun updateJob(id: UUID, dto: JobUpdateDTO): JobResponseDTO
    fun deleteJob(id: UUID)
    fun searchJobs(query: String, pageable: Pageable): PageResponse<JobResponseDTO>
}
