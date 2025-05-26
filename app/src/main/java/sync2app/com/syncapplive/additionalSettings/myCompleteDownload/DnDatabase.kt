package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DnApi::class], version = 1, exportSchema = false)
abstract class DnDatabase : RoomDatabase() {

    abstract fun dnDao(): DnDao

    companion object {
        @Volatile
        private var INSTANCE: DnDatabase? = null

        fun getDatabase(context: Context): DnDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DnDatabase::class.java,
                    "DnApi_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}