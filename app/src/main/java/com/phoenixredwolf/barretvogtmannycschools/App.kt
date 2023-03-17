package com.phoenixredwolf.barretvogtmannycschools

import android.app.Application
import com.phoenixredwolf.barretvogtmannycschools.data.repository.Repository
import com.phoenixredwolf.barretvogtmannycschools.network.SchoolApi.retrofitService
import com.phoenixredwolf.barretvogtmannycschools.network.SchoolDataManager


class App : Application() {

    private val manager by lazy {
        SchoolDataManager(retrofitService)
    }

    val repository by lazy {
        Repository(manager)
    }
}