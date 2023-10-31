package com.piriurna.gradleversionnotifier.data.signin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaveUserRequestBody(
    @SerializedName("userId")
    val id: String,
    @SerializedName("fcmToken")
    val fcmToken: String
): Parcelable
