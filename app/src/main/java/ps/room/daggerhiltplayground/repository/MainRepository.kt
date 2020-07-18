package ps.room.daggerhiltplayground.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ps.room.daggerhiltplayground.model.Blog
import ps.room.daggerhiltplayground.retrofit.BlogRetrofit
import ps.room.daggerhiltplayground.retrofit.NetworkMapper
import ps.room.daggerhiltplayground.room.BlogDao
import ps.room.daggerhiltplayground.room.CacheMapper
import ps.room.daggerhiltplayground.util.DataState
import java.lang.Exception

class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlog():Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
            emit(DataState.Error(e))

        }
    }
}