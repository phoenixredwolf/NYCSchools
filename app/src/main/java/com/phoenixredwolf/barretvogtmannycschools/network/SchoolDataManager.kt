package com.phoenixredwolf.barretvogtmannycschools.network

import com.phoenixredwolf.barretvogtmannycschools.data.model.SATResponse
import com.phoenixredwolf.barretvogtmannycschools.data.model.SchoolsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SchoolDataManager(
    private val service: SchoolDataService
) {

    suspend fun getAllSchools(): SchoolsResponse = withContext(Dispatchers.IO) {
        service.getAllSchools()
    }

    suspend fun getBoroSchools(boro: String): SchoolsResponse = withContext(Dispatchers.IO){
        service.getBoroSchools(boro)
    }

    suspend fun getNeighborhoodSchools(neighborhood: String): SchoolsResponse = withContext(Dispatchers.IO){
        service.getNeighborhoodSchools(neighborhood)
    }

    suspend fun getSchoolsWithLanguage(language: String): SchoolsResponse = withContext(Dispatchers.IO){
        service.getSchoolsWithLanguage(language)
    }

    suspend fun getAllSatScores(): SATResponse = withContext(Dispatchers.IO) {
        service.getAllSatScores()
    }

    suspend fun getSATForSchool(schoolName: String): SATResponse {
        TODO("Not yet implemented")
    }
}