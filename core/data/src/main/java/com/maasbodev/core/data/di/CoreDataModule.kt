package com.maasbodev.core.data.di

import com.maasbodev.core.data.auth.EncryptedSessionStorage
import com.maasbodev.core.domain.SessionStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CoreDataModule {

	@Binds
	abstract fun bindEncryptedSessionStorage(encryptedSessionStorageImpl: EncryptedSessionStorage): SessionStorage
}