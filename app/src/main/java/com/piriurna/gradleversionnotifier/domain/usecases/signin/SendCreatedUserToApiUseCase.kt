package com.piriurna.gradleversionnotifier.domain.usecases.signin

import com.piriurna.gradleversionnotifier.domain.repositories.AuthRepository
import javax.inject.Inject

class SendCreatedUserToApiUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {

    }
}