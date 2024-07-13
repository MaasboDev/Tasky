package com.maasbodev.core.presentation.ui

import com.maasbodev.core.domain.util.DataError

fun DataError.asUiText(): UiText {
	return when (this) {
		DataError.Network.BAD_REQUEST -> UiText.StringResource(
			R.string.error_bad_request
		)

		DataError.Network.CONFLICT -> UiText.StringResource(
			R.string.error_conflict
		)

		DataError.Network.UNAUTHORIZED -> UiText.StringResource(
			R.string.error_unauthorized
		)

		else -> UiText.StringResource(
			R.string.error_unknown
		)
	}
}