package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.profile.CandidateProfileResponseDTO
import com.bojan.recruitment.exceptions.EntityNotFoundException
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.model.CandidateProfile
import com.bojan.recruitment.repository.CandidateProfileRepository
import com.bojan.recruitment.repository.UserRepository
import com.bojan.recruitment.service.CandidateProfileService
import com.bojan.recruitment.service.FileStorageService
import com.bojan.recruitment.util.getCurrentUserId
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CandidateProfileServiceImpl(
    private val profileRepository: CandidateProfileRepository,
    private val userRepository: UserRepository,
    private val fileStorageService: FileStorageService
) : CandidateProfileService {

    override fun uploadCv(file: MultipartFile): CandidateProfileResponseDTO {

        val userId = getCurrentUserId()

        val user = userRepository.findById(userId)
            .orElseThrow {
                EntityNotFoundException("User not found.")
            }

        val profile = profileRepository.findByUserId(userId)
            ?: CandidateProfile(user = user)

        val filePath = fileStorageService.saveFile(file)

        profile.cvFilePath = filePath

        return profileRepository.save(profile).toDto()
    }

    override fun getMyProfile(): CandidateProfileResponseDTO {

        val userId = getCurrentUserId()

        val user = userRepository.findById(userId)
            .orElseThrow { EntityNotFoundException("User not found.") }

        val profile = profileRepository.findByUserId(userId)
            ?: profileRepository.save(CandidateProfile(user = user))

        return profile.toDto()
    }

    override fun downloadCv(): ByteArray {

        val userId = getCurrentUserId()

        val profile = profileRepository.findByUserId(userId)
            ?: throw EntityNotFoundException("Profile not found.")

        val path = profile.cvFilePath
            ?: throw EntityNotFoundException("CV not uploaded.")

        return fileStorageService.loadFile(path)
    }
}
