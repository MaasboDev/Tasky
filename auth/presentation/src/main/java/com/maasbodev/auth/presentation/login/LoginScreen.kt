package com.maasbodev.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.maasbodev.auth.presentation.R
import com.maasbodev.core.presentation.designsystem.components.TaskyActionButton
import com.maasbodev.core.presentation.designsystem.components.TaskyPasswordTextField
import com.maasbodev.core.presentation.designsystem.components.TaskyTextField
import com.maasbodev.core.presentation.designsystem.components.TaskyToolbar
import com.maasbodev.core.presentation.ui.ObserveAsEvents

@Composable
fun LoginScreenRoot(
	onLoginSuccess: () -> Unit,
	onSignUpClick: () -> Unit,
	viewModel: LoginViewModel = hiltViewModel(),
) {
	val context = LocalContext.current
	val keyboardController = LocalSoftwareKeyboardController.current
	ObserveAsEvents(viewModel.events) { event ->
		when (event) {
			is LoginEvent.Error -> {
				keyboardController?.hide()
				Toast.makeText(
					context,
					event.error.asString(context),
					Toast.LENGTH_LONG
				).show()
			}

			LoginEvent.LoginSuccess -> {
				keyboardController?.hide()
				Toast.makeText(
					context,
					R.string.youre_logged_in,
					Toast.LENGTH_LONG
				).show()

				onLoginSuccess()
			}
		}
	}
	LoginScreen(
		state = viewModel.state,
		onAction = { action ->
			when (action) {
				is LoginAction.OnRegisterClick -> onSignUpClick()
				else -> Unit
			}
			viewModel.onAction(action)
		}
	)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
	state: LoginState,
	onAction: (LoginAction) -> Unit,
) {
	Scaffold(topBar = { TaskyToolbar(title = stringResource(R.string.welcome_back)) }) { padding ->
		Column(
			modifier = Modifier
				.padding(padding)
				.background(MaterialTheme.colorScheme.onBackground)
				.fillMaxSize()
		) {
			TaskyTextField(
				state = state.email,
				endIcon = if (state.isValidEmail) Icons.Default.Check else null,
				hint = stringResource(R.string.email_address)
			)
			TaskyPasswordTextField(
				state = state.password,
				isPasswordVisible = state.isPasswordVisible,
				onTogglePasswordVisibility = { onAction(LoginAction.OnTogglePasswordVisibility) },
				hint = stringResource(R.string.password),
			)
			TaskyActionButton(
				text = stringResource(id = R.string.log_in),
				isLoading = state.isLoggingIn,
				enabled = state.canLogin && !state.isLoggingIn,
				onClick = { onAction(LoginAction.OnLoginClick) }
			)
		}
	}
}