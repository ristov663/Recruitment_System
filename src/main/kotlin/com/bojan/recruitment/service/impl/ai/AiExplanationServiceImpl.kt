package com.bojan.recruitment.service.impl.ai

import com.bojan.recruitment.service.ai.AiExplanationService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class AiExplanationServiceImpl(
    private val chatClient: ChatClient
) : AiExplanationService {

    override fun explain(cvText: String, jobText: String, score: Double): String? {

        val prompt = """
            You are an AI recruitment assistant.

            Candidate match score: $score%

            Analyze why the candidate matches or does not match the job.

            CV:
            $cvText

            Job Description:
            $jobText

            Provide a short and professional explanation.
        """.trimIndent()

        return chatClient
            .prompt(prompt)
            .call()
            .content()
    }
}
