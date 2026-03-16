package com.bojan.recruitment.repository

import com.bojan.recruitment.model.Job
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface JobRepository : JpaRepository<Job, UUID> {

    fun findByCreatedById(userId: UUID, pageable: Pageable): Page<Job>

    @Query(
        """
        SELECT j FROM Job j
        WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(j.description) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(j.location) LIKE LOWER(CONCAT('%', :query, '%'))
        """
    )
    fun searchJobs(@Param("query") query: String, pageable: Pageable): Page<Job>
}
