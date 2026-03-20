package com.bojan.recruitment.repository

import com.bojan.recruitment.model.CandidateProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CandidateProfileRepository : JpaRepository<CandidateProfile, UUID> {

    fun findByUserId(userId: UUID): CandidateProfile?
}
