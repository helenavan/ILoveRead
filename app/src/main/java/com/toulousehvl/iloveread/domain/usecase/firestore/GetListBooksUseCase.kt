package com.toulousehvl.iloveread.domain.usecase.firestore

import com.toulousehvl.iloveread.domain.repository.UserRepository
import javax.inject.Inject

class GetListBooksUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun invoke() = userRepository.getListBooks()

}