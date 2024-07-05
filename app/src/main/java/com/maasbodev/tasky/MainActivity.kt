package com.maasbodev.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maasbodev.auth.presentation.register.RegisterScreen
import com.maasbodev.tasky.navigation.Register
import com.maasbodev.tasky.ui.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		installSplashScreen().apply {
			setKeepOnScreenCondition {
				viewModel.state.isCheckingAuth
			}
		}
		setContent {
			TaskyTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					if (!viewModel.state.isCheckingAuth) {
						val navController = rememberNavController()
						NavHost(
							navController = navController,
							startDestination = Register
						) {
							composable<Register> { RegisterScreen() }
						}
					}
				}
			}
		}
	}
}
