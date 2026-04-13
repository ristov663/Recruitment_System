package com.bojan.recruitment.service.impl.ai

import com.bojan.recruitment.service.ai.AiMatchingService
import com.bojan.recruitment.service.ai.EmbeddingService
import com.bojan.recruitment.util.SimilarityUtils
import org.springframework.stereotype.Service

@Service
class AiMatchingServiceImpl(
    private val embeddingService: EmbeddingService
) : AiMatchingService {

    override fun calculateScore(cvText: String, jobText: String): Double {

        val cvEmbedding = embeddingService.embed(cvText)
        val jobEmbedding = embeddingService.embed(jobText)

        val similarity = SimilarityUtils.cosineSimilarity(cvEmbedding, jobEmbedding)

        return similarity * 100
    }
}