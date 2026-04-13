package com.bojan.recruitment.controller

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.application.ApplicationRequestDTO
import com.bojan.recruitment.dto.application.ApplicationResponseDTO
import com.bojan.recruitment.dto.application.ApplicationStatusUpdateDTO
import com.bojan.recruitment.dto.application.MatchExplanationDTO
import com.bojan.recruitment.dto.job.JobResponseDTO
import com.bojan.recruitment.service.ApplicationService
import com.bojan.recruitment.service.ai.JobRecommendationService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/applications")
class ApplicationController(
    private val applicationService: ApplicationService,
    private val jobRecommendationService: JobRecommendationService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('CANDIDATE')")
    fun applyForJob(
        @RequestBody @Valid dto: ApplicationRequestDTO
    ): ApplicationResponseDTO =
        applicationService.applyForJob(dto)

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
    fun updateStatus(
        @PathVariable id: UUID,
        @RequestBody dto: ApplicationStatusUpdateDTO
    ): ApplicationResponseDTO =
        applicationService.updateStatus(id, dto)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getApplication(
        @PathVariable id: UUID
    ): ApplicationResponseDTO =
        applicationService.getApplication(id)

    @GetMapping("/job/{jobId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
    fun getApplicationsByJob(
        @PathVariable jobId: UUID,
        @PageableDefault(15) page: Pageable
    ): PageResponse<ApplicationResponseDTO> =
        applicationService.getApplicationsByJob(jobId, page)

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CANDIDATE')")
    fun getMyApplications(
        @PageableDefault(15) page: Pageable
    ): PageResponse<ApplicationResponseDTO> =
        applicationService.getMyApplications(page)

    @GetMapping("/{id}/explain")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
    fun explainApplication(
        @PathVariable id: UUID
    ): MatchExplanationDTO =
        applicationService.explainApplication(id)

    @GetMapping("/job/{jobId}/best")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
    fun getBestCandidate(
        @PathVariable jobId: UUID
    ): ApplicationResponseDTO =
        applicationService.getBestCandidate(jobId)

    @GetMapping("/{id}/summary")
    @PreAuthorize("hasAnyRole('HR','ADMIN')")
    fun getSummary(@PathVariable id: UUID): String =
        applicationService.getApplicationSummary(id)

    @GetMapping("/recommendations")
    @PreAuthorize("hasRole('CANDIDATE')")
    fun recommendJobs(): List<JobResponseDTO> =
        jobRecommendationService.recommendJobsForCurrentUser()
}
