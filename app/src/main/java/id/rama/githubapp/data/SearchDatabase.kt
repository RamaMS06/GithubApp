package id.rama.githubapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SearchData::class], version = 1, exportSchema = false)
abstract class SearchDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        @Volatile
        private var INSTANCE: SearchDatabase? = null

        fun getDatabase(context: Context): SearchDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchDatabase::class.java,
                    "search_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}