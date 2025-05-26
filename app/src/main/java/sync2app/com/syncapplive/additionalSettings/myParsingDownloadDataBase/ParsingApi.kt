package sync2app.com.syncapplive.additionalSettings.myParsingDownloadDataBase

import androidx.room.Entity

@Entity(tableName = "ParsingApi_table", primaryKeys = ["FolderName", "FileName"])
data class ParsingApi(
    var id: Long = System.currentTimeMillis(),
    var SN: String,
    var FolderName: String,
    var FileName: String,
    var FileTimeStamp: String,
    var Status: String
)
