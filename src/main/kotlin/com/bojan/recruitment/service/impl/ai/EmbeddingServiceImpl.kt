package com.bojan.recruitment.service.impl.ai

import com.bojan.recruitment.service.ai.EmbeddingService
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.stereotype.Service

@Service
class EmbeddingServiceImpl(
    private val embeddingModel: EmbeddingModel
) : EmbeddingService {

    override fun embed(text: String): FloatArray {
        return embeddingModel.embed(text)
    }
}