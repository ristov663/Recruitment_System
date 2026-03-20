package com.bojan.recruitment.service

import com.bojan.recruitment.dto.profile.CandidateProfileResponseDTO
import org.springframework.web.multipart.MultipartFile

interface CandidateProfileService {

    fun uploadCv(file: MultipartFile): CandidateProfileResponseDTO
    fun getMyProfile(): CandidateProfileResponseDTO
    fun downloadCv(): ByteArray
}
