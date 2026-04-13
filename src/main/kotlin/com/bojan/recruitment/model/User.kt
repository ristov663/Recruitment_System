package com.bojan.recruitment.model

import com.bojan.recruitment.dto.user.UserUpdateDTO
import com.bojan.recruitment.enums.UserRole
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Entity
@Table(name = "users")
class User(

    @Id
    @Column(nullable = false, updatable = false)
    val id: UUID = UUID.randomUUID(),
    var firstName: String,
    var lastName: String,
    @Column(unique = true, nullable = false)
    var userName: String,
    @Column(unique = true, nullable = false)
    var email: String,
    var passwordValue: String,
    var role: UserRole
) : UserDetails {
    fun update(dto: UserUpdateDTO) {
        firstName = dto.firstName
        lastName = dto.lastName
        userName = dto.userName
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
    }

    override fun getPassword(): String = passwordValue
    override fun getUsername(): String = userName
}
