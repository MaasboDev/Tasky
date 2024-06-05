package com.maasbodev.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maasbodev.auth.presentation.register.RegisterScreen
import com.maasbodev.tasky.ui.theme.TaskyTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			TaskyTheme {
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					val navController = rememberNavController()
					NavHost(
						navController = navController, startDestination = Register
					) {
						composable<Register> { RegisterScreen() }
					}

				}
			}
		}
	}
}
