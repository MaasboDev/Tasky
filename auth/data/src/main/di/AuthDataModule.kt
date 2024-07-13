package com.maasbodev.auth.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AuthDataModule {

	@Provides
	fun provideAuthRepository(
		httpClient: HttpClient,
		sessionStorage: SessionStorage,
	): AuthRepository {
		return AuthRepositoryImpl(httpClient)
	}

	@Provides
	fun provideHttpClient(): HttpClient {
		return HttpClient()
	}

	@Provides
	fun provideUserDataValidator(patternvalidator: PatternValidator): UserDataValidator {
		return UserDataValidator()
	}
}