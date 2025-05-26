package sync2app.com.syncapplive.additionalSettings.myApiDownload

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface FileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFiles(filesApi: FilesApi)

    @Update
    suspend fun updateFiles(filesApi: FilesApi)

    @Delete
    suspend fun deleteFiles(filesApi: FilesApi)

    @Query("DELETE FROM FilesApi_table")
    suspend fun deleteAllFiles()

    @Query("SELECT * FROM FilesApi_table  ORDER BY id ASC")
    fun readAllData(): LiveData<List<FilesApi>>

    @Query("DELETE FROM FilesApi_table WHERE id NOT IN (SELECT id FROM FilesApi_table ORDER BY id ASC )")
    fun deleteExcessItems()

}