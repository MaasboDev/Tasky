package com.maasbodev.core.data.auth

import com.maasbodev.core.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable = AuthInfoSerializable(
	accessToken = accessToken,
	refreshToken = refreshToken,
	userId = userId,
)

fun AuthInfoSerializable.toAuthInfo(): AuthInfo = AuthInfo(
	accessToken = accessToken,
	refreshToken = refreshToken,
	userId = userId,
)