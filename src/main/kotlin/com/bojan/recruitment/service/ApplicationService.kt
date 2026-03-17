package com.bojan.recruitment.service

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.application.ApplicationRequestDTO
import com.bojan.recruitment.dto.application.ApplicationResponseDTO
import com.bojan.recruitment.dto.application.ApplicationStatusUpdateDTO
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ApplicationService {

    fun applyForJob(dto: ApplicationRequestDTO): ApplicationResponseDTO
    fun updateStatus(id: UUID, dto: ApplicationStatusUpdateDTO): ApplicationResponseDTO
    fun getApplication(id: UUID): ApplicationResponseDTO
    fun getApplicationsByJob(jobId: UUID, pageable: Pageable): PageResponse<ApplicationResponseDTO>
    fun getMyApplications(pageable: Pageable): PageResponse<ApplicationResponseDTO>
}
