package sync2app.com.syncapplive.additionalSettings.myParsingDownloadDataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParsingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFiles(parsingApi: ParsingApi)

    @Update
    suspend fun updateFiles(parsingApi: ParsingApi)

    @Delete
    suspend fun deleteFiles(parsingApi: ParsingApi)

    @Query("DELETE FROM ParsingApi_table")
    suspend fun deleteAllFiles()

    @Query("SELECT * FROM ParsingApi_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<ParsingApi>>

    @Query("DELETE FROM ParsingApi_table WHERE id NOT IN (SELECT id FROM ParsingApi_table ORDER BY id ASC)")
    suspend fun deleteExcessItems()

    @Query("SELECT * FROM ParsingApi_table WHERE FolderName = :folderName AND FileName = :fileName LIMIT 1")
    suspend fun getFileByFolderAndFileName(folderName: String, fileName: String): ParsingApi?
}
