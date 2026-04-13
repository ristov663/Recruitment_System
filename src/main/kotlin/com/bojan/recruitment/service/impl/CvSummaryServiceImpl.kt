package com.bojan.recruitment.service.impl

import com.bojan.recruitment.service.CvSummaryService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service

@Service
class CvSummaryServiceImpl(
    private val chatClient: ChatClient
) : CvSummaryService {

    override fun summarize(cvText: String): String {

        val prompt = """
            Summarize this CV into a short professional profile.
            
            Focus on:
            - Experience
            - Skills
            - Technologies
            
            CV:
            $cvText
        """.trimIndent()

        return chatClient
            .prompt(prompt)
            .call()
            .content().toString()
    }
}
