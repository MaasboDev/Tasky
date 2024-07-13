import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.ksp.devtools)
}

android {
	namespace = "com.maasbodev.core.data"
	compileSdk = 34

	defaultConfig {
		minSdk = 24

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")

		val apiKey = gradleLocalProperties(rootDir, providers).getProperty("API_KEY")
		buildConfigField("String", "API_KEY", "\"$apiKey\"")
		val baseUrl = gradleLocalProperties(rootDir, providers).getProperty("BASE_URL")
		buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
	}

	buildFeatures {
		buildConfig = true
	}

	buildTypes {
		release {
			isMinifyEnabled = false
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
}

dependencies {
	implementation(projects.core.domain)

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.kotlinx.serialization.json)
	implementation(libs.material)
	implementation(libs.timber)

	// Ktor
	implementation(libs.ktor.client.auth)
	implementation(libs.ktor.client.cio)
	implementation(libs.ktor.client.content.negotiation)
	implementation(libs.ktor.client.core)
	implementation(libs.ktor.client.logging)
	implementation(libs.ktor.serialization.kotlinx.json)

	// Hilt
	implementation(libs.hilt.android)
	ksp(libs.hilt.compiler)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}