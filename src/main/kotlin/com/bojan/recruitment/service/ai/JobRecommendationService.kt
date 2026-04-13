package com.bojan.recruitment.service.ai

import com.bojan.recruitment.dto.job.JobResponseDTO

interface JobRecommendationService {

    fun recommendJobsForCurrentUser(): List<JobResponseDTO>
}
