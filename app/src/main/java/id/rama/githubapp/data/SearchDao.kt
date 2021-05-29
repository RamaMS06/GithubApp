package id.rama.githubapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSearchQuery(query : SearchData)

    @Query("SELECT * FROM search_table ORDER BY id ASC")
    fun readSearchQuery() : LiveData<List<SearchData>>
}