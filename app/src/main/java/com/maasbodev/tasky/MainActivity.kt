package com.maasbodev.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maasbodev.tasky.ui.theme.TaskyTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			TaskyTheme {
				val navController = rememberNavController()
				val title = remember { mutableStateOf("") }
				Scaffold(topBar = {
					CenterAlignedTopAppBar(title = { Text(text = title.value) })
				}) {
					NavHost(
						modifier = Modifier.padding(it),
						navController = navController,
						startDestination = Register
					) {
						composable<Register> {
							title.value = stringResource(R.string.create_your_account)
						}
					}

				}
			}
		}
	}
}
