package com.example.seventhweekapppt3.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FavCatItemDto(
    @SerialName("image_id")
    val image_id: String,
)