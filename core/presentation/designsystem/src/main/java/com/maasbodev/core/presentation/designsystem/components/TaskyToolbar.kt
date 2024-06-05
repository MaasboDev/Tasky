package com.maasbodev.core.presentation.designsystem.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskyToolbar(
	modifier: Modifier = Modifier,
	title: String,
) {
	CenterAlignedTopAppBar(title = { Text(text = title) })
}