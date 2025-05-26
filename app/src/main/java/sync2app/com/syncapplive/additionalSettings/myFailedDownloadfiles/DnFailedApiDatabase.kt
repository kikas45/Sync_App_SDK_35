package sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DnFailedApi::class], version = 1, exportSchema = false)
abstract class DnFailedApiDatabase : RoomDatabase() {

    abstract fun dnFailedDao(): DnFailedApiDao

    companion object {
        @Volatile
        private var INSTANCE: DnFailedApiDatabase? = null

        fun getDatabase(context: Context): DnFailedApiDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DnFailedApiDatabase::class.java,
                    "DnFailedApi_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}