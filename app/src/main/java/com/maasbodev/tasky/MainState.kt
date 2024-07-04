package com.maasbodev.tasky

data class MainState(
	val isLoggedIn: Boolean = false,
	val isCheckingAuth: Boolean = false,
)
