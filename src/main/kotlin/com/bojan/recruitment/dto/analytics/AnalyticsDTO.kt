package com.bojan.recruitment.dto.analytics

data class AnalyticsDTO(
    val totalUsers: Long,
    val totalJobs: Long,
    val totalApplications: Long,
    val avgMatchScore: Double
)
