package com.maasbodev.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
	val email: String,
	val password: String,
)
