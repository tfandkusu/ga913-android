package com.tfandkusu.ga913android.data

import android.content.Context
import com.tfandkusu.ga913android.data.schema.LandmarkJsonSchema
import com.tfandkusu.ga913android.model.Landmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface LandmarkRepository {
    suspend fun list(): Flow<List<Landmark>>

    suspend fun get(id: Long): Flow<Landmark>

    suspend fun favorite(
        id: Long,
        favorite: Boolean,
    )
}

internal class LandmarkRepositoryImpl
    @Inject
    constructor(
        private val context: Context,
    ) : LandmarkRepository {
        private val data = MutableStateFlow<List<Landmark>>(listOf())

        override suspend fun list(): Flow<List<Landmark>> {
            if (data.value.isEmpty()) {
                val jsonString =
                    context.assets
                        .open(
                            "landmarkData.json",
                        ).bufferedReader()
                        .readText()
                val json = Json { ignoreUnknownKeys = true }
                val landmarkList = json.decodeFromString<List<LandmarkJsonSchema>>(jsonString)
                data.value = landmarkList.map {
                        Landmark(
                            id = it.id,
                            name = it.name,
                            state = it.state,
                            isFavorite = it.isFavorite,
                            park = it.park,
                            description = it.description,
                            imageUrl = "file:///android_asset/${it.imageName}.jpg",
                        )
                    }
            }
            return data
        }

        override suspend fun get(id: Long): Flow<Landmark> =
            list().map { list ->
                list.first { it.id == id }
            }

        override suspend fun favorite(
            id: Long,
            favorite: Boolean,
        ) {
            data.value =
                data.value.map {
                    if (it.id == id) {
                        it.copy(isFavorite = favorite)
                    } else {
                        it
                    }
                }
        }
    }
