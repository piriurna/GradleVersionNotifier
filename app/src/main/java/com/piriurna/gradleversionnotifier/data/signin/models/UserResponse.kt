package com.piriurna.gradleversionnotifier.data.signin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("fcmToken")
    val fcmToken: String,
    @SerializedName("projects")
    val projects: List<ProjectResponse>? = emptyList()
): Parcelable