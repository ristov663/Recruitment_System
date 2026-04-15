package com.bojan.recruitment.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "interviews")
class Interview(

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    var application: Application,

    var scheduledAt: LocalDateTime,

    var interviewer: String,

    var notes: String? = null,

    var score: Double? = null

)
