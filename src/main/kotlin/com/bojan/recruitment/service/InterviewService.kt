package com.bojan.recruitment.service

import com.bojan.recruitment.dto.interview.*
import java.util.*

interface InterviewService {

    fun schedule(dto: InterviewRequestDTO): InterviewResponseDTO
    fun scoreInterview(id: UUID, dto: InterviewScoreDTO): InterviewResponseDTO
    fun getByApplication(applicationId: UUID): List<InterviewResponseDTO>
}
