package entities

import com.thelonedev.plugins.CUID
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: String,
    val name: String,
    val createdDt: Instant,
    val updatedDt: Instant
) {
    constructor(name: String): this(
        id = CUID.generateCUID(),
        name = name,
        createdDt = Clock.System.now(),
        updatedDt = Clock.System.now()
    )
}
