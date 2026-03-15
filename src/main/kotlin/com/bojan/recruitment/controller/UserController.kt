package com.bojan.recruitment.controller

import com.bojan.recruitment.dto.PageResponse
import com.bojan.recruitment.dto.user.UserRequestDTO
import com.bojan.recruitment.dto.user.UserResponseDTO
import com.bojan.recruitment.dto.user.UserUpdateDTO
import com.bojan.recruitment.enums.UserRole
import com.bojan.recruitment.service.UserService
import com.bojan.recruitment.util.getCurrentUserId
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllUsers(@PageableDefault(15) page: Pageable): PageResponse<UserResponseDTO> = userService.getAllUsers(page)

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(): UserResponseDTO {
        val id = getCurrentUserId()
        return userService.getUserById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid userRequestDto: UserRequestDTO): UserResponseDTO {
        return userService.createUser(userRequestDto)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@RequestBody @Valid user: UserUpdateDTO): UserResponseDTO {
        val id = getCurrentUserId()
        return userService.updateUser(id, user)
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserById() {
        val id = getCurrentUserId()
        return userService.deleteUser(id)
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun searchUsers(
        @RequestParam query: String,
        @PageableDefault(15) page: Pageable
    ): PageResponse<UserResponseDTO> {
        return userService.searchUser(query, page)
    }

    @GetMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    fun getUserByUsername(
        @RequestParam username: String
    ): UserResponseDTO {
        return userService.getUserByUsername(username)
    }

    @GetMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    fun getUsersByRole(
        @RequestParam role: UserRole,
        @PageableDefault(15) page: Pageable
    ): PageResponse<UserResponseDTO> {
        if (role == UserRole.ADMIN) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        return userService.getUsersByRole(role, page)
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    fun getUserByEmail(
        @RequestParam email: String
    ): UserResponseDTO {
        return userService.getUsersByEmail(email)
    }
}
