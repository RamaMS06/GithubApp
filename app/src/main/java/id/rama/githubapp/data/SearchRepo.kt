package id.rama.githubapp.data

import androidx.lifecycle.LiveData

class SearchRepo(private val searchDao: SearchDao) {

    val readSearchQuery : LiveData<List<SearchData>> = searchDao.readSearchQuery()

    suspend fun addSearchQuery(searchData : SearchData){
        searchDao.addSearchQuery(searchData)
    }
}