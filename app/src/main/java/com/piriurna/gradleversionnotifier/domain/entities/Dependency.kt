package com.piriurna.gradleversionnotifier.domain.entities

data class Dependency(
    val group: String,
    val name: String,
    val latestVersion: Version
)