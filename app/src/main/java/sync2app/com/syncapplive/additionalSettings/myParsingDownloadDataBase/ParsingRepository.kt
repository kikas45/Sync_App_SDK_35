package sync2app.com.syncapplive.additionalSettings.myParsingDownloadDataBase

import androidx.lifecycle.LiveData

class ParsingRepository(private val parsingDao: ParsingDao) {

    val readAllData: LiveData<List<ParsingApi>> = parsingDao.readAllData()

    suspend fun addFiles(parsingApi: ParsingApi) {
        parsingDao.addFiles(parsingApi)
    }

    suspend fun updateFiles(parsingApi: ParsingApi) {
        parsingDao.updateFiles(parsingApi)
    }

    suspend fun deleteFiles(parsingApi: ParsingApi) {
        parsingDao.deleteFiles(parsingApi)
    }

    suspend fun deleteAllFiles() {
        parsingDao.deleteAllFiles()
    }

    suspend fun deleteExcessItems() {
        parsingDao.deleteExcessItems()
    }

    suspend fun getFileByFolderAndFileName(folderName: String, fileName: String): ParsingApi? {
        return parsingDao.getFileByFolderAndFileName(folderName, fileName)
    }
}
