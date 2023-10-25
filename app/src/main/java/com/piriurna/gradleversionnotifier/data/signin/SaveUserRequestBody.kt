package com.piriurna.gradleversionnotifier.data.signin

import android.os.Parcelable
import com.piriurna.gradleversionnotifier.data.signin.models.ProjectResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveUserRequestBody(
    val id: String,
    val fcmToken: String,
    val projects: List<ProjectResponse> = emptyList()
): Parcelable
