package com.bojan.recruitment.service

import com.bojan.recruitment.controller.auth.AuthenticationRequest
import com.bojan.recruitment.controller.auth.AuthenticationResponse

interface AuthenticationService {

    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse
    fun refreshAccessToken(token: String): String?
    fun logout(token: String)
}
