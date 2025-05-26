package sync2app.com.syncapplive.additionalSettings.myCompleteDownload


import androidx.lifecycle.LiveData
class DnRepository(private val dnDao: DnDao) {

    val readAllData: LiveData<List<DnApi>> = dnDao.readAllData()

    suspend fun addFiles(filesApi: DnApi) {
        dnDao.addFiles(filesApi)
    }

    suspend fun updateFiles(filesApi: DnApi) {
        dnDao.updateFiles(filesApi)
    }

    suspend fun deleteFiles(filesApi: DnApi) {
        dnDao.deleteFiles(filesApi)
    }

    suspend fun deleteAllFiles() {
        dnDao.deleteAllFiles()
    }

    suspend fun deleteExcessItems() {
        dnDao.deleteExcessItems()
    }
}
