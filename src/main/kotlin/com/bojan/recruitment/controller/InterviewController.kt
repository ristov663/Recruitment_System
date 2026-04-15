package com.bojan.recruitment.controller

import com.bojan.recruitment.dto.interview.*
import com.bojan.recruitment.service.InterviewService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/interviews")
class InterviewController(
    private val interviewService: InterviewService
) {

    @PostMapping
    fun schedule(@RequestBody dto: InterviewRequestDTO) =
        interviewService.schedule(dto)

    @PutMapping("/{id}/score")
    fun score(@PathVariable id: UUID, @RequestBody dto: InterviewScoreDTO) =
        interviewService.scoreInterview(id, dto)

    @GetMapping("/application/{id}")
    fun getByApplication(@PathVariable id: UUID) =
        interviewService.getByApplication(id)
}
