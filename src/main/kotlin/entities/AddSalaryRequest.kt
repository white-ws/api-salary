package entities

import kotlinx.serialization.Serializable

@Serializable
data class AddSalaryRequest(
    val companyName: String,
    val level: String,
    val field: String,
    val yoe: Int,
    val tc: Long
)