package sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles

import androidx.room.Entity
@Entity(tableName = "DnFailedApi_table", primaryKeys = ["SN", "FolderName", "FileName", "Status"])
data class DnFailedApi(
    var id: Long = System.currentTimeMillis(),
    var SN: String,
    var FolderName: String,
    var FileName: String,
    var Status: String,
)

