package com.capstone.rangkayo.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.rangkayo.BuildConfig.API_HOST
import com.capstone.rangkayo.BuildConfig.API_KEY
import com.capstone.rangkayo.data.source.remote.api.Service
import com.capstone.rangkayo.data.source.remote.response.Responses
import com.jetpack.moviecatalogue2.utils.ErrorMessageHandler
import com.capstone.rangkayo.util.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: Service) {

    fun getNews(): LiveData<ApiResponse<Responses>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<Responses>>()
        CoroutineScope(IO).launch {
            try {
                val response = apiService.getNews(API_KEY, API_HOST,"Mental health").await()
                if (response.articles.isEmpty()) {
                    result.postValue(ApiResponse.empty("No Data Found", response))
                } else {
                    result.postValue(ApiResponse.success(response))
                }
            } catch (e: Exception) {
                result.postValue(ApiResponse.error(ErrorMessageHandler.generateErrorMessage(e)))
            }
        }
        EspressoIdlingResource.decrement()
        return result
    }

}