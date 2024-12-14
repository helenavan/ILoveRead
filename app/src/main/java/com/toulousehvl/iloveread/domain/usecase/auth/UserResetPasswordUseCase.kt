package com.toulousehvl.iloveread.domain.usecase.auth

import com.toulousehvl.iloveread.domain.repository.UserRepository
import javax.inject.Inject

class UserResetPasswordUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email: String) = userRepository.resetPassword(email)

}