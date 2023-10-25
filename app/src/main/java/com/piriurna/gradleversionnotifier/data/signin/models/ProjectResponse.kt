package com.piriurna.gradleversionnotifier.data.signin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProjectResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("dependencies")
    val dependencies: List<DependencyResponse>
) : Parcelable