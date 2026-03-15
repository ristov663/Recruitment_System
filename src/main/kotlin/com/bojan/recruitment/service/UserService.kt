package com.bojan.recruitment.service

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.user.UserRequestDTO
import com.bojan.recruitment.dto.user.UserResponseDTO
import com.bojan.recruitment.dto.user.UserUpdateDTO
import com.bojan.recruitment.enums.UserRole
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

interface UserService {

    fun getAllUsers(pageable: Pageable): PageResponse<UserResponseDTO>
    fun getUserById(id: UUID): UserResponseDTO
    fun createUser(userRequestDto: UserRequestDTO): UserResponseDTO
    fun updateUser(id: UUID, updateDto: UserUpdateDTO): UserResponseDTO
    fun deleteUser(id: UUID)
    fun searchUser(query: String, pageable: Pageable): PageResponse<UserResponseDTO>
    fun getUserByUsername(username: String): UserResponseDTO
    fun getUsersByRole(role: UserRole, pageable: Pageable): PageResponse<UserResponseDTO>
    fun getUsersByEmail(email: String): UserResponseDTO
    fun loadUserByUsername(username: String): UserDetails
}
