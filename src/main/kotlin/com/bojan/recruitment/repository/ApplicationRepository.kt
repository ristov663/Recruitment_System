package com.bojan.recruitment.repository

import com.bojan.recruitment.model.Application
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ApplicationRepository : JpaRepository<Application, UUID> {

    fun findByCandidateId(candidateId: UUID, pageable: Pageable): Page<Application>
    fun findByJobId(jobId: UUID, pageable: Pageable): Page<Application>
    fun findByJobIdOrderByMatchScoreDesc(jobId: UUID, pageable: Pageable): Page<Application>
}
