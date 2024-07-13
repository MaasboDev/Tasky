package com.maasbodev.auth.data

import com.maasbodev.auth.domain.AuthRepository
import com.maasbodev.core.data.networking.post
import com.maasbodev.core.domain.AuthInfo
import com.maasbodev.core.domain.SessionStorage
import com.maasbodev.core.domain.util.DataError
import com.maasbodev.core.domain.util.EmptyResult
import com.maasbodev.core.domain.util.Result
import com.maasbodev.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
	private val httpClient,
	private val sessionStorage: SessionStorage,
) : AuthRepository {

	override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
		val result = httpClient.post<LoginRequest, LoginResponse>(
			route = "/login",
			body = LoginRequest(
				email = email,
				password = password
			)
		)
		if (result is Result.Success) {
			sessionStorage.set(
				AuthInfo(
					accessToken = result.data.accessToken,
					refreshToken = result.data.refreshToken,
					userId = result.data.userId
				)
			)
		}
		return result.asEmptyDataResult()
	}

	override suspend fun register(
		fullName: String,
		email: String,
		password: String,
	): EmptyResult<DataError.Network> {
		return httpClient.post<RegisterRequest, Unit>(
			route = "/register",
			body = RegisterRequest(
				email = email,
				password = password
			)
		)
	}
}