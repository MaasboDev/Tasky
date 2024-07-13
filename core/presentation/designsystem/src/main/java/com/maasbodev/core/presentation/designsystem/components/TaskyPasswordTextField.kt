package com.maasbodev.core.presentation.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.maasbodev.core.presentation.designsystem.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskyPasswordTextField(
	state: TextFieldState,
	isPasswordVisible: Boolean,
	onTogglePasswordVisibility: () -> Unit,
	hint: String,
	modifier: Modifier = Modifier,
) {
	var isFocused by remember {
		mutableStateOf(false)
	}
	Column(
		modifier = modifier
	) {
		BasicSecureTextField(
			state = state,
			textObfuscationMode = if (isPasswordVisible) {
				TextObfuscationMode.Visible
			} else TextObfuscationMode.Hidden,
			textStyle = LocalTextStyle.current.copy(
				color = MaterialTheme.colorScheme.onBackground
			),
			keyboardType = KeyboardType.Password,
			cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
			modifier = Modifier
				.clip(RoundedCornerShape(16.dp))
				.background(
					if (isFocused) {
						MaterialTheme.colorScheme.primary.copy(
							alpha = 0.05f
						)
					} else {
						MaterialTheme.colorScheme.surface
					}
				)
				.border(
					width = 1.dp,
					color = if (isFocused) {
						MaterialTheme.colorScheme.primary
					} else {
						Color.Transparent
					},
					shape = RoundedCornerShape(16.dp)
				)
				.padding(horizontal = 12.dp)
				.onFocusChanged {
					isFocused = it.isFocused
				},
			decorator = { innerBox ->
				Row(
					modifier = Modifier
						.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically
				) {
					Box(
						modifier = Modifier
							.weight(1f)
					) {
						if (state.text.isEmpty() && !isFocused) {
							Text(
								text = hint,
								color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
									alpha = 0.4f
								),
								modifier = Modifier.fillMaxWidth()
							)
						}
						innerBox()
					}
					IconButton(onClick = onTogglePasswordVisibility) {
						Icon(
							imageVector = if (!isPasswordVisible) {
								Icons.Default.Visibility
							} else Icons.Default.VisibilityOff,
							contentDescription = if (isPasswordVisible) {
								stringResource(id = R.string.show_password)
							} else {
								stringResource(id = R.string.hide_password)
							},
							tint = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
				}
			}
		)
	}
}