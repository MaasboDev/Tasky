package com.maasbodev.core.domain.util

sealed interface DataError : Error {
	enum class Network : DataError {
		BAD_REQUEST,
		CONFLICT,
		NO_INTERNET,
		PAYLOAD_TOO_LARGE,
		REQUEST_TIMEOUT,
		SERVER_ERROR,
		SERIALIZATION,
		TOO_MANY_REQUESTS,
		UNAUTHORIZED,
		UNKNOWN,
	}

	enum class Local : DataError {
		DISK_FULL
	}
}