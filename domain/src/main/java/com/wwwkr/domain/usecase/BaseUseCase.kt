package com.wwwkr.domain.usecase

import com.wwwkr.domain.model.Base
import com.wwwkr.domain.repository.BaseRepository
import javax.inject.Inject

class BaseUseCase @Inject constructor(private val baseRepository: BaseRepository) {

    suspend fun insertBaseData(testList : List<String>) {

        val itemList = testList.map {
            Base(test = it)
        }

        return baseRepository.insertBaseData(items = itemList)
    }
}