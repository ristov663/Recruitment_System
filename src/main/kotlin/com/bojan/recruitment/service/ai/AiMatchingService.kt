package com.bojan.recruitment.service.ai

interface AiMatchingService {

    fun calculateScore(cvText: String, jobText: String): Double
}