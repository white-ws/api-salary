package entities

import com.thelonedev.plugins.CUID
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: String,
    val name: String
) {
    constructor(name: String): this(
        id = CUID.generateCUID(),
        name = name
    )
}
