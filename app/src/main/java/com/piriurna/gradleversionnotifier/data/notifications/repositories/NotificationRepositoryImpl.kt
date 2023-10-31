package com.piriurna.gradleversionnotifier.data.notifications.repositories

import com.google.firebase.messaging.FirebaseMessaging
import com.piriurna.gradleversionnotifier.data.ResponseWrapper
import com.piriurna.gradleversionnotifier.domain.notifications.repositories.NotificationRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging
) : NotificationRepository {

    override suspend fun getToken(): ResponseWrapper<String> {
        return try {
            val token = firebaseMessaging.token.await()
            ResponseWrapper.Success(token)
        } catch (e: Exception) {
            ResponseWrapper.Error(e)
        }
    }
}