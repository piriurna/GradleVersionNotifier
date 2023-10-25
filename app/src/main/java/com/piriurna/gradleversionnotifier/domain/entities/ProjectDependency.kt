package com.piriurna.gradleversionnotifier.domain.entities

data class ProjectDependency(
    val dependency: Dependency,
    val currentVersion: Version
) {
}