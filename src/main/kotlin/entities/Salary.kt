package entities

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val id: String,
    val company: Company,
    val level: String,
    val field: String,
    val yoe: Int,
    val tc: Long,
    val createdDt: Instant
)