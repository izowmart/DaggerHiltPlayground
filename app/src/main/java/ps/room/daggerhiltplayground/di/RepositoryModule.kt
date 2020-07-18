package ps.room.daggerhiltplayground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ps.room.daggerhiltplayground.repository.MainRepository
import ps.room.daggerhiltplayground.retrofit.BlogRetrofit
import ps.room.daggerhiltplayground.retrofit.NetworkMapper
import ps.room.daggerhiltplayground.room.BlogDao
import ps.room.daggerhiltplayground.room.CacheMapper
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)

    }
}