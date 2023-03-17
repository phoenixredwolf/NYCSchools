package com.phoenixredwolf.barretvogtmannycschools.data.repository

import com.phoenixredwolf.barretvogtmannycschools.network.SchoolDataManager

class Repository(private val manager: SchoolDataManager) {
    suspend fun getAllSchools() = manager.getAllSchools()
    suspend fun getBoroSchools(boro:String) = manager.getBoroSchools(boro)
    suspend fun getNeighborhoodSchools(neighborhood:String) = manager.getNeighborhoodSchools(neighborhood)
    suspend fun getSchoolsWithLanguage(language:String) = manager.getSchoolsWithLanguage(language)
    suspend fun getAllSatScores() = manager.getAllSatScores()

}