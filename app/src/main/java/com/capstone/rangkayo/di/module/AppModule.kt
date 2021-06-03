package com.capstone.rangkayo.di.module

import android.app.Application
import com.capstone.rangkayo.data.source.remote.api.Service
import com.capstone.rangkayo.data.source.NewsRepository
import com.capstone.rangkayo.data.source.local.room.NewsDao
import com.capstone.rangkayo.data.source.local.LocalDataSource
import com.capstone.rangkayo.data.source.local.room.NewsDatabase
import com.capstone.rangkayo.data.source.remote.RemoteDataSource
import com.capstone.rangkayo.viewModel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideNewsDatabase(application: Application): NewsDatabase =
            NewsDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao =
            newsDatabase.movieDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(newsDao: NewsDao): LocalDataSource =
            LocalDataSource(newsDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: Service): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideNewsRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): NewsRepository = NewsRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(movieRepository: NewsRepository): ViewModelFactory =
            ViewModelFactory(movieRepository)

    }
}