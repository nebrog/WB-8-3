package com.example.seventhweekapppt3.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CatItemDto(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
)