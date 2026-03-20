package com.bojan.recruitment.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "candidate_profiles")
class CandidateProfile(

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    val id: UUID = UUID.randomUUID(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    var user: User,

    var education: String? = null,

    var experienceYears: Int? = null,

    @Column(length = 2000)
    var skills: String? = null,

    var cvFilePath: String? = null

)
