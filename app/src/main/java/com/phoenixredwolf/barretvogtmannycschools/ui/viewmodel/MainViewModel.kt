package com.phoenixredwolf.barretvogtmannycschools.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.phoenixredwolf.barretvogtmannycschools.App
import com.phoenixredwolf.barretvogtmannycschools.data.model.SchoolsResponse
import com.phoenixredwolf.barretvogtmannycschools.utility.MockData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = getApplication<App>().repository

    private val _schoolResponse = MutableStateFlow(SchoolsResponse())
    val schoolsResponse: StateFlow<SchoolsResponse>
        get() = _schoolResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError : StateFlow<Boolean> = _isError

    private val errorHandler = CoroutineExceptionHandler {
        _, error ->
        if(error is Exception) {
            _isError.value = true
        }
    }

    fun getAllSchools() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _schoolResponse.value = repository.getAllSchools()
            _isLoading.value = false
        }
    }

}