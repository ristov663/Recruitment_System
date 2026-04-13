package com.bojan.recruitment.service.ai

interface EmbeddingService {

    fun embed(text: String): FloatArray
}