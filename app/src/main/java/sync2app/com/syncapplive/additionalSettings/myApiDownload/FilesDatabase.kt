package sync2app.com.syncapplive.additionalSettings.myApiDownload

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilesApi::class], version = 1, exportSchema = false)
abstract class FilesDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao

    companion object {
        @Volatile
        private var INSTANCE: FilesDatabase? = null

        fun getDatabase(context: Context): FilesDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilesDatabase::class.java,
                    "FilesApi_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}