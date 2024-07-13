package com.maasbodev.tasky.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maasbodev.auth.presentation.login.LoginScreenRoot
import com.maasbodev.auth.presentation.register.RegisterScreen

@Composable
fun NavigationRoot(
	navController: NavHostController,
	isLoggedIn: Boolean,
) {
	NavHost(
		navController = navController,
		startDestination = if (isLoggedIn) LoginNavObject else LoginNavObject
	) {
		composable<LoginNavObject> {
			LoginScreenRoot(
				onLoginSuccess = {
					navController.navigate("run") {
						popUpTo("auth") {
							inclusive = true
						}
					}
				},
				onSignUpClick = {
					navController.navigate(RegisterNavObject)
				}
			)
		}
		composable<RegisterNavObject> {
			RegisterScreen()
		}
	}
}