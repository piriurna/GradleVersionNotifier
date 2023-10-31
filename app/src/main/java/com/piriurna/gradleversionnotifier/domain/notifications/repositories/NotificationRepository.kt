package com.piriurna.gradleversionnotifier.domain.notifications.repositories

import com.piriurna.gradleversionnotifier.data.ResponseWrapper

interface NotificationRepository {

    suspend fun getToken(): ResponseWrapper<String>
}