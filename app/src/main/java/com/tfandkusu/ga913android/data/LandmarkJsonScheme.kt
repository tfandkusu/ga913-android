package com.tfandkusu.ga913android.data
import kotlinx.serialization.Serializable

@Serializable
data class LandmarkJsonScheme(
    val id: Long,
    val name: String,
    val state: String,
    val isFavorite: Boolean,
    val park: String,
    val description: String,
    val imageUrl: String,
)
