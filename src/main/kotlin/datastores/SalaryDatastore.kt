package datastores

import entities.Company
import entities.Salary
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toKotlinInstant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.`java-time`.timestamp
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

object SalaryTable : Table("salaries") {
    val id: Column<String> = varchar("id", 30)
    val companyId: Column<String> = varchar("company_id", 30) references CompanyTable.id
    val level: Column<String> = varchar("level", 50)
    val field: Column<String> = varchar("field", 50)
    val yoe: Column<Int> = integer("yoe")
    val tc: Column<Long> = long("tc")
    val createdDt: Column<Instant> = timestamp("created_dt")
}

private fun ResultRow.toSalary() = Salary(
    id = this[SalaryTable.id],
    company = Company(name = this[CompanyTable.name]),
    level = this[SalaryTable.level],
    field = this[SalaryTable.field],
    yoe = this[SalaryTable.yoe],
    tc = this[SalaryTable.tc],
    createdDt = this[SalaryTable.createdDt].toKotlinInstant()
)

class SalaryDatastore {
    fun create(salary: Salary) {
        transaction {
            SalaryTable.insert {
                it[id] = salary.id
                it[companyId] = salary.company.id
                it[level] = salary.level
                it[field] = salary.field
                it[yoe] = salary.yoe
                it[tc] = salary.tc
                it[createdDt] = salary.createdDt.toJavaInstant()
            }
        }
    }

    fun getAll(): List<Salary> {
        return transaction {
            (SalaryTable innerJoin CompanyTable).select { SalaryTable.companyId eq CompanyTable.id }
                .map(ResultRow::toSalary)
        }
    }
}