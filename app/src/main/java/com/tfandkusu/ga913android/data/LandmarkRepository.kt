package com.tfandkusu.ga913android.data

import android.content.Context
import com.tfandkusu.ga913android.data.schema.LandmarkJsonSchema
import com.tfandkusu.ga913android.model.Landmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

interface LandmarkRepository {
    suspend fun list(): Flow<List<Landmark>>

    suspend fun get(id: Long): Flow<Landmark>

    suspend fun favorite(
        id: Long,
        favorite: Boolean,
    )
}

internal class LandmarkRepositoryImpl(
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
            val landmarkList = Json.decodeFromString<List<LandmarkJsonSchema>>(jsonString)
            data.value =
                landmarkList.map {
                    Landmark(
                        id = it.id,
                        name = it.name,
                        state = it.state,
                        isFavorite = it.isFavorite,
                        park = it.park,
                        description = it.description,
                        imageUrl = it.imageUrl,
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
