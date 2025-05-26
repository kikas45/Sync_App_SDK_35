package sync2app.com.syncapplive.additionalSettings.myApiDownload

import androidx.room.Entity
@Entity(tableName = "FilesApi_table", primaryKeys = ["SN", "FolderName", "FileName", "Status"])
data class FilesApi(
    var id: Long = System.currentTimeMillis(),
    var SN: String,
    var FolderName: String,
    var FileName: String,
    var Status: String,
)

