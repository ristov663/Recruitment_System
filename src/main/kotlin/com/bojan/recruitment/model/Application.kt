package com.bojan.recruitment.model

import com.bojan.recruitment.enums.ApplicationStatus
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "applications")
class Application(

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    var candidate: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    var job: Job,

    @Enumerated(EnumType.STRING)
    var status: ApplicationStatus = ApplicationStatus.APPLIED,

    var appliedAt: LocalDateTime = LocalDateTime.now(),

    var cvUrl: String? = null

) {

    fun updateStatus(newStatus: ApplicationStatus) {
        status = newStatus
    }
}
