package sync2app.com.syncapplive.additionalSettings.myParsingDownloadDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParsingViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<ParsingApi>>
    private val repository: ParsingRepository

    init {
        val fileDao = ParsingDatabase.getDatabase(application).parsingDao()
        repository = ParsingRepository(fileDao)
        readAllData = repository.readAllData
    }

    fun addFiles(parsingApi: ParsingApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFiles(parsingApi)
        }
    }

    fun updateFiles(parsingApi: ParsingApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFiles(parsingApi)
        }
    }

    fun deleteFiles(parsingApi: ParsingApi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFiles(parsingApi)
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

    fun getAllFiles(): LiveData<List<ParsingApi>> {
        return repository.readAllData
    }

    suspend fun getFileByFolderAndFileName(folderName: String, fileName: String): ParsingApi? {
        return withContext(Dispatchers.IO) {
            repository.getFileByFolderAndFileName(folderName, fileName)
        }
    }
}
