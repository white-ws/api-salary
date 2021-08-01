package usecases

import datastores.CompanyDatastore
import datastores.SalaryDatastore
import entities.AddSalaryRequest
import entities.Company
import entities.Salary

class AddSalary(
    private val salaryDatastore: SalaryDatastore,
    private val companyDatastore: CompanyDatastore
) {
    operator fun invoke(request: AddSalaryRequest) {
        val company: Company = companyDatastore.findCompanyByName(request.companyName) ?: createAndReturnANewCompany(request.companyName)
        val salary = Salary(
            company = company,
            level = request.level,
            field = request.field,
            yoe = request.yoe,
            tc = request.tc
        )
    }

    private fun createAndReturnANewCompany(name: String): Company {
        val newCompany = Company(name = name)
        companyDatastore.create(newCompany)

        return newCompany
    }
}