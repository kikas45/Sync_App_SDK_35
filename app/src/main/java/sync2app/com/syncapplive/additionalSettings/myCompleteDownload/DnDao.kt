package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface DnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFiles(dnApi: DnApi)

    @Update
    suspend fun updateFiles(dnApi: DnApi)

    @Delete
    suspend fun deleteFiles(dnApi: DnApi)

    @Query("DELETE FROM DnApi_table")
    suspend fun deleteAllFiles()

    @Query("SELECT * FROM DnApi_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<DnApi>>

    @Query("DELETE FROM DnApi_table WHERE id NOT IN (SELECT id FROM DnApi_table ORDER BY id ASC )")
    fun deleteExcessItems()

}
