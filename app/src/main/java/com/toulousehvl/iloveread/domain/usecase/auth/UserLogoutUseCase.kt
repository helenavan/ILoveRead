package com.toulousehvl.iloveread.domain.usecase.auth

import com.toulousehvl.iloveread.domain.repository.UserRepository
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.logout()
}