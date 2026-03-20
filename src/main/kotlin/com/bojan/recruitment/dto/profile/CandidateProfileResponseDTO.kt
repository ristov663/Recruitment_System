package com.bojan.recruitment.dto.profile

import java.util.*

data class CandidateProfileResponseDTO(

    val id: UUID,
    val userId: UUID,
    val education: String?,
    val experienceYears: Int?,
    val skills: String?,
    val cvFilePath: String?

)
