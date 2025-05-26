package sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface DnFailedApiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFiles(dFailedApi: DnFailedApi)

    @Update
    suspend fun updateFiles(dFailedApi: DnFailedApi)

    @Delete
    suspend fun deleteFiles(dFailedApi: DnFailedApi)

    @Query("DELETE FROM DnFailedApi_table")
    suspend fun deleteAllFiles()

    @Query("SELECT * FROM DnFailedApi_table  ORDER BY id ASC")
    fun readAllData(): LiveData<List<DnFailedApi>>

    @Query("DELETE FROM DnFailedApi_table WHERE id NOT IN (SELECT id FROM DnFailedApi_table ORDER BY id ASC )")
    fun deleteExcessItems()

}