package com.alife.vegan.data.repository

import com.alife.vegan.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(

) : CalendarRepository {
    override fun getDietList(): Flow<List<Pair<String, String>>> = flow {
        // emit(DietList)
    }
}