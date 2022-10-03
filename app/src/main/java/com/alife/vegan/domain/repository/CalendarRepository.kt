package com.alife.vegan.domain.repository

import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
  fun getDietList(): Flow<List<Pair<String, String>>>
}