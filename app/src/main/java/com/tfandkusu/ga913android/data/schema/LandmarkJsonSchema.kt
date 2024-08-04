package com.tfandkusu.ga913android.data.schema
import kotlinx.serialization.Serializable

@Serializable
data class LandmarkJsonSchema(
    val id: Long,
    val name: String,
    val state: String,
    val isFavorite: Boolean,
    val park: String,
    val description: String,
    val imageName: String,
)
