package sync2app.com.syncapplive.additionalSettings.ApiUrls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ApiUrlViewModel : ViewModel() {

    private val _apiUrls = MutableLiveData<Api_Ur_lModels>()
    val apiUrls: LiveData<Api_Ur_lModels> = _apiUrls

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable.message)
    }

    fun fetchApiUrls(fullUrl: String) {
        val apiService = RetrofitInstance_Ap_iUrls.createApiService(fullUrl)

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            try {
                val response = apiService.getAppConfig(fullUrl)
                if (response.isSuccessful) {
                    _apiUrls.postValue(response.body())
                } else {
                    _error.postValue("Bad request")
                }
            } catch (e: HttpException) {
                _error.postValue("HTTP Exception: ${e.message()}")
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }
}

