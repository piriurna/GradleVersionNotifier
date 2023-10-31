package com.piriurna.gradleversionnotifier.data.di

import com.google.firebase.messaging.FirebaseMessaging
import com.piriurna.gradleversionnotifier.BuildConfig
import com.piriurna.gradleversionnotifier.data.notifications.repositories.NotificationRepositoryImpl
import com.piriurna.gradleversionnotifier.data.signin.network.AuthRepositoryImpl
import com.piriurna.gradleversionnotifier.data.signin.network.AuthService
import com.piriurna.gradleversionnotifier.domain.notifications.repositories.NotificationRepository
import com.piriurna.gradleversionnotifier.domain.signin.repositories.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(500, TimeUnit.SECONDS)
            .connectTimeout(500, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    fun provideFirebaseMessaging(): FirebaseMessaging =
        FirebaseMessaging.getInstance()

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    fun provideNotificationRepository(firebaseMessaging: FirebaseMessaging): NotificationRepository {
        return NotificationRepositoryImpl(firebaseMessaging)
    }
}