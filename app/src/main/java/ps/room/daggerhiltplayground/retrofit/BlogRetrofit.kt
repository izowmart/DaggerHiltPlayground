package ps.room.daggerhiltplayground.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun get() : List<BlogNetworkEntity>
}