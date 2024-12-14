package com.toulousehvl.iloveread.domain.usecase.auth

import com.toulousehvl.iloveread.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUiUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke() = flow { emit(userRepository.userUid()) }

}