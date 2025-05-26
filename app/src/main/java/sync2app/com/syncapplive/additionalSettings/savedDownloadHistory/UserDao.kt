package sync2app.com.syncapplive.additionalSettings.savedDownloadHistory

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


    @Dao
    interface UserDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addUser(user: User)

        @Update
        suspend fun updateUser(user: User)

        @Delete
        suspend fun deleteUser(user: User)

        @Query("DELETE FROM user_table")
        suspend fun deleteAllUsers()

        @Query("SELECT * FROM user_table  ORDER BY id DESC")
        fun readAllData(): LiveData<List<User>>

        @Query("DELETE FROM user_table WHERE id NOT IN (SELECT id FROM user_table ORDER BY id DESC )")
        fun deleteExcessItems()

    }