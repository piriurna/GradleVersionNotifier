package com.piriurna.gradleversionnotifier.domain.entities

data class User(
    val id: String,
    val fcmToken: String,
    val projects: List<Project>
)
