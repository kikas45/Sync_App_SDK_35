package sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sync2app.com.syncapplive.additionalSettings.myApiDownload.FilesApi

class DnFailedViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<DnFailedApi>>
    private val repository: DnFailedRepository

    init {
        val fileDao = DnFailedApiDatabase.getDatabase(application).dnFailedDao()
        repository = DnFailedRepository(fileDao)
        readAllData = repository.readAllData
    }

    fun addFiles(dnFailedApi: DnFailedApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFiles(dnFailedApi)
        }
    }


    fun updateFiles(dnFailedApi: DnFailedApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFiles(dnFailedApi)
        }
    }

    fun deleteFiles(dnFailedApi: DnFailedApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFiles(dnFailedApi)
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

    fun getAllFiles(): LiveData<List<DnFailedApi>> {
        return repository.readAllData
    }
}
