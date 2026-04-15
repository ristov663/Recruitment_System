package com.bojan.recruitment.repository

import com.bojan.recruitment.model.Interview
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface InterviewRepository : JpaRepository<Interview, UUID> {

    fun findByApplicationId(applicationId: UUID): List<Interview>
}
