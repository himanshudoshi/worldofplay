package com.worldofplay.app.stories.list.di

import com.worldofplay.app.stories.list.data.remote.TopStoriesRemoteDataSource
import com.worldofplay.app.stories.list.data.remote.TopStoriesRepository
import com.worldofplay.app.stories.list.data.remote.TopStoriesService
import com.worldofplay.app.stories.list.usecases.TopStoriesUsecase
import com.worldofplay.core.di.CoreDataModule
import com.worldofplay.core.qualifiers.RetrofitStories
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class])
open class TopStoriesModule {

    @Singleton
    @Provides
    fun provideTopStoriesUsecase(repository: TopStoriesRepository): TopStoriesUsecase {
        return TopStoriesUsecase(repository)
    }

    @Singleton
    @Provides
    fun provideTopStoriesRepository(remoteDataSource: TopStoriesRemoteDataSource): TopStoriesRepository {
        return TopStoriesRepository(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideTopStoriesRemoteDataSource(topStoriesService : TopStoriesService): TopStoriesRemoteDataSource {
        return TopStoriesRemoteDataSource(topStoriesService)
    }

    @Singleton
    @Provides
    fun provideTopStoriesService(@RetrofitStories retrofit: Retrofit): TopStoriesService {
        return retrofit.create<TopStoriesService>(TopStoriesService::class.java)
    }

}