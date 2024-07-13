package com.maasbodev.auth.domain

import com.maasbodev.core.domain.util.DataError
import com.maasbodev.core.domain.util.EmptyResult

interface AuthRepository {
	suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
	suspend fun register(
		fullName: String,
		email: String,
		password: String,
	): EmptyResult<DataError.Network>
}