import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.org.jetbrains.kotlin.kapt)
	alias(libs.plugins.hilt.plugin)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.ksp.devtools)
}

android {
	namespace = "com.maasbodev.tasky"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.maasbodev.tasky"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}

		val apiKey = gradleLocalProperties(rootDir, providers).getProperty("API_KEY")
		buildConfigField("String", "API_KEY", "\"$apiKey\"")
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	implementation(projects.core.data)
	implementation(projects.core.domain)

	implementation(projects.auth.data)
	implementation(projects.auth.domain)
	implementation(projects.auth.presentation)

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)

	// Splash screen
	implementation(libs.androidx.core.splashscreen)

	// Hilt
	implementation(libs.hilt.android)
	ksp(libs.hilt.compiler)

	// Navigation
	implementation(libs.navigation.compose)
	implementation(libs.kotlinx.serialization.json)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}

// Allow references to generated code
kapt {
	correctErrorTypes = true
}
