package com.piriurna.gradleversionnotifier.domain.entities

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int
) {
}