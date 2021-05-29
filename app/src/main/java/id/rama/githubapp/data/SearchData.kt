package id.rama.githubapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchData(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val searchQuery : String
)