package com.phoenixredwolf.barretvogtmannycschools.network

import com.phoenixredwolf.barretvogtmannycschools.data.model.SATResponse
import com.phoenixredwolf.barretvogtmannycschools.data.model.SchoolsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolDataService {

    @GET("s3k6-pzi2.json")
    suspend fun getAllSchools(
        @Query("\$limit") limit: Int = 20
    ) : SchoolsResponse

    @GET("s3k6-pzi2.json")
    suspend fun getBoroSchools(
        @Query("boro") boro: String
    ) : SchoolsResponse

    @GET("s3k6-pzi2.json")
    suspend fun getNeighborhoodSchools(
        @Query("neighborhood") neighborhood: String
    ) : SchoolsResponse

    @GET("s3k6-pzi2.json")
    suspend fun getSchoolsWithLanguage(
        @Query("language_classes") language: String
    ) : SchoolsResponse

    @GET("f9bf-2cp4.json")
    suspend fun getAllSatScores() : SATResponse

    @GET("f9bf-2cp4.json")
    suspend fun getSATForSchool(
        @Query("school_name") schoolName: String
    ) : SATResponse

}