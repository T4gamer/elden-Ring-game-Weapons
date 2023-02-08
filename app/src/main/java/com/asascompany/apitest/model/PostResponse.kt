package com.asascompany.apitest.model

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class Welcome (
    val success: Boolean,
    val count: Long,
    val total: Long,
    val data: List<Datum>
)

@Serializable
data class Datum (
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val attack: List<Attack>,
    val defence: List<Attack>,
    val scalesWith: List<ScalesWith>,
    val requiredAttributes: List<Attack>,
    val category: String,
    val weight: Double
)

@Serializable
data class Attack (
    val name: String,
    val amount: Long
){
    override fun toString():String{
        return "$name:$amount"
    }
}

@Serializable
data class ScalesWith (
    val name: String,
    val scaling: String

){
    override fun toString():String{
        return "$name:$scaling"
    }
}
