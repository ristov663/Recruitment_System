package com.bojan.recruitment.controller

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.job.JobRequestDTO
import com.bojan.recruitment.dto.job.JobResponseDTO
import com.bojan.recruitment.dto.job.JobUpdateDTO
import com.bojan.recruitment.service.JobService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/jobs")
class JobController(
    private val jobService: JobService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllJobs(@PageableDefault(15) page: Pageable): PageResponse<JobResponseDTO> =
        jobService.getAllJobs(page)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getJobById(@PathVariable id: UUID): JobResponseDTO =
        jobService.getJobById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createJob(@RequestBody @Valid dto: JobRequestDTO): JobResponseDTO =
        jobService.createJob(dto)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateJob(
        @PathVariable id: UUID,
        @RequestBody @Valid dto: JobUpdateDTO
    ): JobResponseDTO =
        jobService.updateJob(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteJob(@PathVariable id: UUID) {
        jobService.deleteJob(id)
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun searchJobs(
        @RequestParam query: String,
        @PageableDefault(15) page: Pageable
    ): PageResponse<JobResponseDTO> =
        jobService.searchJobs(query, page)

}
