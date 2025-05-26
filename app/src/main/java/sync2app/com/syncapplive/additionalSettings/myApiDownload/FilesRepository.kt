package sync2app.com.syncapplive.additionalSettings.myApiDownload


import androidx.lifecycle.LiveData

class FilesRepository(private val filesDao: FileDao) {

    val readAllData: LiveData<List<FilesApi>> = filesDao.readAllData()

    suspend fun addFiles(filesApi: FilesApi) {
        filesDao.addFiles(filesApi)
    }

    suspend fun updateFiles(filesApi: FilesApi) {
        filesDao.updateFiles(filesApi)
    }

    suspend fun deleteFiles(filesApi: FilesApi) {
        filesDao.deleteFiles(filesApi)
    }

    suspend fun deleteAllFiles() {
        filesDao.deleteAllFiles()
    }

    suspend fun deleteExcessItems() {
        filesDao.deleteExcessItems()
    }

}