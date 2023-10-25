package com.piriurna.gradleversionnotifier.data.signin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DependencyResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val group: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("latestVersion")
    val latestVersion: String,
    @SerializedName("usersInterested")
    val usersInterested: List<UserResponse>
): Parcelable
