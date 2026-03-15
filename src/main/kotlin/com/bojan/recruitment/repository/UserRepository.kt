package com.bojan.recruitment.repository

import com.bojan.recruitment.enums.UserRole
import com.bojan.recruitment.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findByRole(role: UserRole, pageable: Pageable): Page<User>
    fun findByUserName(username: String): User
    fun findByEmail(email: String): User
    fun existsByUserName(username: String): Boolean
    fun existsByEmail(email: String): Boolean

    @Query(
        """
    SELECT u FROM User u 
    WHERE (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :query, '%')) 
           OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%')) 
           OR LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :query, '%')))
      AND u.role <> com.bojan.recruitment.enums.UserRole.ADMIN
    """
    )
    fun searchUser(@Param("query") query: String, pageable: Pageable): Page<User>

}
