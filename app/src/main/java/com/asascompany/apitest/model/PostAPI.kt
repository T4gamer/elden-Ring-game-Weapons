package com.asascompany.apitest.model

interface PostsApi {
    var page : Int
    suspend fun getPage(): List<Datum>
    suspend fun getName(name : String): Datum
    suspend fun getId(id : String): Datum
    fun emptyDatum() :Datum
    fun emptyDatum(id: String) :Datum
}