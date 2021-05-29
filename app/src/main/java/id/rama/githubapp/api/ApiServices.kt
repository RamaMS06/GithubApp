package id.rama.githubapp.api

import id.rama.githubapp.model.ModelDetailUsers
import id.rama.githubapp.model.ResponseUsers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    //Search All Users
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") q: String,
        @Query("per_page") per_page: Int,
        @Query("page") page : Int
    ): Response<ResponseUsers>

    //Detail Users
    @GET("users/{username}")
    suspend fun getDetailUsers(@Path("username") username: String): Response<ModelDetailUsers>
}