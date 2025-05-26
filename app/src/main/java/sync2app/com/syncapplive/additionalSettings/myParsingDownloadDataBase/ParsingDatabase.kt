package sync2app.com.syncapplive.additionalSettings.myParsingDownloadDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ParsingApi::class], version = 1, exportSchema = false)
abstract class ParsingDatabase : RoomDatabase() {

    abstract fun parsingDao(): ParsingDao

    companion object {
        @Volatile
        private var INSTANCE: ParsingDatabase? = null

        fun getDatabase(context: Context): ParsingDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParsingDatabase::class.java,
                    "ParsingApi_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}