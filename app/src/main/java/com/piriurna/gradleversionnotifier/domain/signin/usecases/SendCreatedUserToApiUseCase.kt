package com.piriurna.gradleversionnotifier.domain.signin.usecases

import com.piriurna.gradleversionnotifier.data.ResponseWrapper
import com.piriurna.gradleversionnotifier.data.asResult
import com.piriurna.gradleversionnotifier.data.signin.SaveUserRequestBody
import com.piriurna.gradleversionnotifier.domain.entities.User
import com.piriurna.gradleversionnotifier.domain.notifications.repositories.NotificationRepository
import com.piriurna.gradleversionnotifier.domain.signin.repositories.AuthRepository
import javax.inject.Inject

class SendCreatedUserToApiUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val notificationRepository: NotificationRepository
) {

    suspend operator fun invoke(
        uuid: String,
    ): Result<User> {
        return when(val fcmTokenResult = notificationRepository.getToken()) {
            is ResponseWrapper.Success -> {
                val fcmToken = fcmTokenResult.data
                val userRequestBody = SaveUserRequestBody(uuid, fcmToken)
                val userResponse = authRepository.saveUserToDatabase(userRequestBody)

                userResponse.asResult { User(id = "sdjakd", fcmToken = fcmToken, projects = emptyList()) }
            }
            else -> {
                Result.failure(Exception("Failed to retrieve fcm token"))
            }
        }
    }
}