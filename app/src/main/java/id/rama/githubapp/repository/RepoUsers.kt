package id.rama.githubapp.repository

import id.rama.githubapp.api.RetrofitInstance
import id.rama.githubapp.model.ModelDetailUsers
import id.rama.githubapp.model.ResponseUsers
import retrofit2.Response

class RepoUsers {

    suspend fun searchUsers(q : String, per_page : Int, page: Int) : Response<ResponseUsers>{
        return RetrofitInstance.apiListUsers.searchUsers(q,per_page,page)
    }

    suspend fun getDetailUsers(username : String): Response<ModelDetailUsers>{
        return RetrofitInstance.apiListUsers.getDetailUsers(username)
    }
}