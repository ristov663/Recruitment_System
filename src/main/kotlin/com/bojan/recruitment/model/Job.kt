package com.bojan.recruitment.model

import com.bojan.recruitment.dto.job.JobUpdateDTO
import com.bojan.recruitment.dto.user.UserUpdateDTO
import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "jobs")
class Job(

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, length = 3000)
    var description: String,

    @Column(nullable = false, length = 2000)
    var requirements: String,

    @Column(nullable = false)
    var location: String,

    @Column(nullable = false)
    var experienceLevel: String,

    @Column(nullable = false)
    var salaryRange: String,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    var createdBy: User

) {
    fun update(dto: JobUpdateDTO) {
        this.title = dto.title
        this.description = dto.description
        this.requirements = dto.requirements
        this.location = dto.location
        this.experienceLevel = dto.experienceLevel
        this.salaryRange = dto.salaryRange
    }
}
