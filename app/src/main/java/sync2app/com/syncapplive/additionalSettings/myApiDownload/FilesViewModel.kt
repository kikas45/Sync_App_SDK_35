package sync2app.com.syncapplive.additionalSettings.myApiDownload

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles.DnFailedApi

class FilesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<FilesApi>>
    private val repository: FilesRepository

    init {
        val fileDao = FilesDatabase.getDatabase(application).fileDao()
        repository = FilesRepository(fileDao)
        readAllData = repository.readAllData
    }

    fun addFiles(filesApi: FilesApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFiles(filesApi)
        }
    }

    fun updateFiles(filesApi: FilesApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFiles(filesApi)
        }
    }

    fun deleteFiles(filesApi: FilesApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFiles(filesApi)
        }
    }

    fun deleteExcessItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExcessItems()
        }
    }

    fun deleteAllFiles() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFiles()
        }
    }
    fun addMultipleFiles(dnFailedApis: List<FilesApi>) {
        viewModelScope.launch(Dispatchers.IO) {
            dnFailedApis.forEach { repository.addFiles(it) }
        }
    }
}
