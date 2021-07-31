package entities

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: String,
    val name: String
)
