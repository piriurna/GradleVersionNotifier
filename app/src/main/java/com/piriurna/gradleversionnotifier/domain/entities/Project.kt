package com.piriurna.gradleversionnotifier.domain.entities

data class Project(
    val id: String,
    val name: String,
    val dependencies: List<Dependency>,
    val owners: List<User>
)