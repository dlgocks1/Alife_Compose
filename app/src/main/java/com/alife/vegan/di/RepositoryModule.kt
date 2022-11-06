package com.alife.vegan.di

import android.app.Application
import com.alife.vegan.data.repository.CalendarRepositoryImpl
import com.alife.vegan.data.repository.RegisterDietRepositoryImpl
import com.alife.vegan.domain.repository.CalendarRepository
import com.alife.vegan.domain.repository.RegisterDietRepository
import com.alife.vegan.network.AlifeService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {


    @Provides
    @ViewModelScoped
    fun provideCalendarRepo(): CalendarRepository {
        return CalendarRepositoryImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideRegisterDietRepo(alifeService: AlifeService): RegisterDietRepository {
        return RegisterDietRepositoryImpl(alifeService)
    }

//  @Provides
//  @Singleton
//  fun provideAppDatabase(
//    application: Application
//  ): AppDatabase {
//    return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
//      .fallbackToDestructiveMigration().build()
//  }

}