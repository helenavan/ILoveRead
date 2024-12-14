package com.toulousehvl.iloveread.domain.usecase.auth

import com.toulousehvl.iloveread.data.repository.remote.service.UserImpl
import com.toulousehvl.iloveread.domain.repository.UserRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email: String, password: String) =
        userRepository.register(email, password)

}