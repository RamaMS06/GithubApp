package id.rama.githubapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application){
    val readAllData : LiveData<List<SearchData>>
    private val repository : SearchRepo

    init {
        val searchDao = SearchDatabase.getDatabase(application).searchDao()
        repository = SearchRepo(searchDao)
        readAllData = repository.readSearchQuery
    }

    fun addSearchQuery(searchData: SearchData){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSearchQuery(searchData)
        }
    }
}