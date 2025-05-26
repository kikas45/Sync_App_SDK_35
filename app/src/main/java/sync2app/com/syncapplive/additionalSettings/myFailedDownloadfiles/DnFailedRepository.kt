package sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles


import androidx.lifecycle.LiveData

class DnFailedRepository(private val dnFailedApiDao: DnFailedApiDao) {

    val readAllData: LiveData<List<DnFailedApi>> = dnFailedApiDao.readAllData()

    suspend fun addFiles(dnFailedApi: DnFailedApi) {
        dnFailedApiDao.addFiles(dnFailedApi)
    }

    suspend fun updateFiles(dnFailedApi: DnFailedApi) {
        dnFailedApiDao.updateFiles(dnFailedApi)
    }

    suspend fun deleteFiles(dnFailedApi: DnFailedApi) {
        dnFailedApiDao.deleteFiles(dnFailedApi)
    }

    suspend fun deleteAllFiles() {
        dnFailedApiDao.deleteAllFiles()
    }

    suspend fun deleteExcessItems() {
        dnFailedApiDao.deleteExcessItems()
    }



}