package datastores

import entities.Company
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toKotlinInstant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.`java-time`.timestamp
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

object CompanyTable : Table("companies") {
    val id: Column<String> = varchar("id", 30)
    val name: Column<String> = varchar("name", 50)
    val createdDt: Column<Instant> = timestamp("created_dt")
    val updatedDt: Column<Instant> = timestamp("updated_dt")
}

private fun ResultRow.toCompany() = Company(
    id = this[CompanyTable.id],
    name = this[CompanyTable.name],
    createdDt = this[CompanyTable.createdDt].toKotlinInstant(),
    updatedDt = this[CompanyTable.updatedDt].toKotlinInstant()
)

class CompanyDatastore {
    fun findCompanyByName(companyName: String): Company? {
        val company = transaction {
            CompanyTable.select { CompanyTable.name eq companyName }.firstOrNull()
        }?.toCompany()

        return company
    }

    fun create(company: Company) {
        transaction {
            CompanyTable.insert {
                it[id] = company.id
                it[name] = company.name
                it[createdDt] = company.createdDt.toJavaInstant()
                it[updatedDt] = company.updatedDt.toJavaInstant()
            }
        }
    }
}