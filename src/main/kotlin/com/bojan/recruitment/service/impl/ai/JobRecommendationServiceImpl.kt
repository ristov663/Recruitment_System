package com.bojan.recruitment.service.impl.ai

import com.bojan.recruitment.dto.job.JobResponseDTO
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.repository.JobRepository
import com.bojan.recruitment.repository.CandidateProfileRepository
import com.bojan.recruitment.service.ai.AiMatchingService
import com.bojan.recruitment.service.ai.JobRecommendationService
import com.bojan.recruitment.util.getCurrentUserId
import org.springframework.stereotype.Service

@Service
class JobRecommendationServiceImpl(
    private val jobRepository: JobRepository,
    private val profileRepository: CandidateProfileRepository,
    private val aiMatchingService: AiMatchingService
) : JobRecommendationService {

    override fun recommendJobsForCurrentUser(): List<JobResponseDTO> {

        val userId = getCurrentUserId()

        val profile = profileRepository.findByUserId(userId)
            ?: return emptyList()

        val skillsText = profile.skills ?: ""

        val jobs = jobRepository.findAll()

        return jobs.map { job ->
            val score = aiMatchingService.calculateScore(
                skillsText,
                job.description ?: ""
            )
            Pair(job, score)
        }
            .sortedByDescending { it.second }
            .take(5)
            .map { it.first.toDto() }
    }
}
