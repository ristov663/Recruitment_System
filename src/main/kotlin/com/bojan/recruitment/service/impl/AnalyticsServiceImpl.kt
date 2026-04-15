package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.analytics.AnalyticsDTO
import com.bojan.recruitment.repository.*
import com.bojan.recruitment.service.AnalyticsService
import org.springframework.stereotype.Service

@Service
class AnalyticsServiceImpl(
    private val userRepository: UserRepository,
    private val jobRepository: JobRepository,
    private val applicationRepository: ApplicationRepository
) : AnalyticsService {

    override fun getAnalytics(): AnalyticsDTO {

        val applications = applicationRepository.findAll()

        val avgScore = applications.mapNotNull { it.matchScore }.average()

        return AnalyticsDTO(
            totalUsers = userRepository.count(),
            totalJobs = jobRepository.count(),
            totalApplications = applicationRepository.count(),
            avgMatchScore = if (avgScore.isNaN()) 0.0 else avgScore
        )
    }
}
