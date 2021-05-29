package id.rama.githubapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rama.githubapp.model.ModelDetailUsers
import id.rama.githubapp.model.ResponseUsers
import id.rama.githubapp.repository.RepoUsers
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelUsers(var repo: RepoUsers) : ViewModel() {

    val searchResponseUsers: MutableLiveData<Response<ResponseUsers>> = MutableLiveData()
    val detailRespomseUsers : MutableLiveData<Response<ModelDetailUsers>> = MutableLiveData()

    fun searchUsers(q: String, per_page: Int, page: Int) {
        viewModelScope.launch {
            val response = repo.searchUsers(q, per_page, page)
            searchResponseUsers.value = response
        }
    }

    fun getDetailUsers(username : String){
        viewModelScope.launch {
            val response = repo.getDetailUsers(username)
            detailRespomseUsers.value = response

        }
    }
}