package com.bojan.recruitment.service.ai

interface AiExplanationService {

    fun explain(cvText: String, jobText: String, score: Double): String?
}