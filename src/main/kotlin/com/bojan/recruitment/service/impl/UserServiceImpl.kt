package com.bojan.recruitment.service.impl

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.user.UserRequestDTO
import com.bojan.recruitment.dto.user.UserResponseDTO
import com.bojan.recruitment.dto.user.UserUpdateDTO
import com.bojan.recruitment.enums.UserRole
import com.bojan.recruitment.exceptions.EntityNotFoundException
import com.bojan.recruitment.exceptions.InvalidEntityException
import com.bojan.recruitment.mapper.toDto
import com.bojan.recruitment.mapper.toPageDto
import com.bojan.recruitment.mapper.toUserEntity
import com.bojan.recruitment.repository.UserRepository
import com.bojan.recruitment.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun getAllUsers(pageable: Pageable): PageResponse<UserResponseDTO> {
        return userRepository.findAll(pageable).map { it.toDto() }.toPageDto()
    }

    override fun getUserById(id: UUID): UserResponseDTO {
        return userRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("User with ID $id not found.")
            }.toDto()
    }

    override fun createUser(userRequestDto: UserRequestDTO): UserResponseDTO {
        val saverUser = userRepository.save(userRequestDto.toUserEntity())
        return saverUser.toDto()
    }

    @Transactional
    override fun updateUser(id: UUID, updateDto: UserUpdateDTO): UserResponseDTO {
        if (userRepository.existsByUserName(updateDto.userName)) {
            throw InvalidEntityException("User with this username already exists.")
        }
        val user = userRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("User with ID $id not found.")
            }

        user.update(updateDto)
        return userRepository.save(user).toDto()
    }

    override fun deleteUser(id: UUID) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        }
    }

    override fun searchUser(query: String, pageable: Pageable): PageResponse<UserResponseDTO> {
        return userRepository.searchUser(query, pageable).map { it.toDto() }.toPageDto()
    }

    override fun getUserByUsername(username: String): UserResponseDTO {
        return userRepository.findByUserName(username).toDto()
    }

    override fun getUsersByRole(role: UserRole, pageable: Pageable): PageResponse<UserResponseDTO> {
        return userRepository.findByRole(role, pageable).map { it.toDto() }.toPageDto()
    }

    override fun getUsersByEmail(email: String): UserResponseDTO {
        return userRepository.findByEmail(email).toDto()
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username)
    }
}
