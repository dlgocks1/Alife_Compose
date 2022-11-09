package com.alife.vegan.di

import com.alife.vegan.data.repository.FoodRepositoryImpl
import com.alife.vegan.data.repository.RegisterDietRepositoryImpl
import com.alife.vegan.domain.repository.FoodRepository
import com.alife.vegan.domain.repository.RegisterDietRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideCalendarRepo(
        calendarRepositoryImpl: FoodRepositoryImpl
    ): FoodRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsUserInfoRepo(
        registerDietRepositoryImpl: RegisterDietRepositoryImpl
    ): RegisterDietRepository


}