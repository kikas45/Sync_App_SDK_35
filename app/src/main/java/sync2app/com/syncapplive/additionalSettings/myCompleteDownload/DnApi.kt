package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import androidx.room.Entity
@Entity(tableName = "DnApi_table", primaryKeys = ["SN", "FolderName", "FileName", "Status"])
data class DnApi(
    var id: Long = System.currentTimeMillis(),
    var SN: String,
    var FolderName: String,
    var FileName: String,
    var Status: String,
)

