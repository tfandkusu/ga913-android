package com.tfandkusu.ga913android.model

data class Landmark(
    val id: Long,
    val name: String,
    val state: String,
    val isFavorite: Boolean,
    val park: String,
    val description: String,
    val imageUrl: String,
)
