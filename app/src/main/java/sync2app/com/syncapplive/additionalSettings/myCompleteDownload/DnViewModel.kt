package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DnViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<DnApi>>
    private val repository: DnRepository

    init {
        val fileDao = DnDatabase.getDatabase(application).dnDao()
        repository = DnRepository(fileDao)
        readAllData = repository.readAllData
    }

    fun addFiles(filesApi: DnApi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFiles(filesApi)
        }
    }

    fun updateFiles(filesApi: DnApi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFiles(filesApi)
        }
    }

    fun deleteFiles(filesApi: DnApi){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFiles(filesApi)
        }
    }

    fun deleteExcessItems(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExcessItems()
        }
    }

    fun deleteAllFiles(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFiles()
        }
    }
}
