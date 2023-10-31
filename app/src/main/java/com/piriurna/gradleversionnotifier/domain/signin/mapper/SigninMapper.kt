package com.piriurna.gradleversionnotifier.domain.signin.mapper

import com.piriurna.gradleversionnotifier.data.signin.models.DependencyResponse
import com.piriurna.gradleversionnotifier.data.signin.models.ProjectResponse
import com.piriurna.gradleversionnotifier.data.signin.models.UserResponse
import com.piriurna.gradleversionnotifier.domain.entities.Dependency
import com.piriurna.gradleversionnotifier.domain.entities.Project
import com.piriurna.gradleversionnotifier.domain.entities.User
import com.piriurna.gradleversionnotifier.domain.entities.Version

fun UserResponse.toResult(): User {
    return User(
        id = id,
        projects = projects?.map { it.toResult() }.orEmpty(),
        fcmToken = fcmToken
    )
}

fun ProjectResponse.toResult(): Project {
    return Project(
        id = id,
        name = name,
        dependencies = dependencies.map { it.toResult() },
        owners = emptyList()
    )
}

// DEpendency as result
fun DependencyResponse.toResult(): Dependency {
    return Dependency(
        group = group,
        name = name,
        latestVersion = latestVersion.toVersion()
    )
}

fun String.toVersion(): Version {
    val split = split(".")
    return Version(
        major = split[0].toInt(),
        minor = split[1].toInt(),
        patch = split[2].toInt()
    )
}
