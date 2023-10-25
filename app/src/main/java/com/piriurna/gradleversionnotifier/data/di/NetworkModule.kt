package com.piriurna.gradleversionnotifier.data.di

import com.piriurna.gradleversionnotifier.BuildConfig
import com.piriurna.gradleversionnotifier.data.signin.network.AuthRepositoryImpl
import com.piriurna.gradleversionnotifier.data.signin.network.AuthService
import com.piriurna.gradleversionnotifier.domain.repositories.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }
}