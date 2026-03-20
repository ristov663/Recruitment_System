package com.bojan.recruitment.controller

import com.bojan.recruitment.dto.profile.CandidateProfileResponseDTO
import com.bojan.recruitment.service.CandidateProfileService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/profile")
class CandidateProfileController(
    private val profileService: CandidateProfileService
) {

    @PostMapping("/cv")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CANDIDATE')")
    fun uploadCv(@RequestParam file: MultipartFile): CandidateProfileResponseDTO =
        profileService.uploadCv(file)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CANDIDATE')")
    fun getMyProfile(): CandidateProfileResponseDTO =
        profileService.getMyProfile()

    @GetMapping("/cv")
    @PreAuthorize("hasRole('CANDIDATE')")
    fun downloadCv(): ByteArray =
        profileService.downloadCv()

}
