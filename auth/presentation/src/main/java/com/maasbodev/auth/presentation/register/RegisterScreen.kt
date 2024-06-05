package com.maasbodev.auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maasbodev.auth.presentation.R
import com.maasbodev.core.presentation.designsystem.components.TaskyToolbar

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
	Scaffold(topBar = { TaskyToolbar(title = stringResource(R.string.create_your_account)) }) { padding ->
		Column(
			modifier = modifier
				.padding(padding)
				.background(MaterialTheme.colorScheme.onBackground)
				.fillMaxSize()
		) {

		}
	}
}

@Preview
@Composable
private fun RegisterScreenPreview() {
	RegisterScreen()
}