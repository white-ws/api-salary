package entities

import com.thelonedev.plugins.CUID
import kotlinx.datetime.Clock
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
) {
    constructor(company: Company, level: String, field: String, yoe: Int, tc: Long): this(
        id = CUID.generateCUID(),
        company = company,
        level = level,
        field = field,
        yoe = yoe,
        tc = tc,
        createdDt = Clock.System.now()
    )
}