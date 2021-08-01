package usecases

import entities.AddSalaryRequest
import entities.Company
import entities.Salary

class AddSalary {
    operator fun invoke(request: AddSalaryRequest) {
        val company = Company(name = request.companyName)
        val salary = Salary(
            company = company,
            level = request.level,
            field = request.field,
            yoe = request.yoe,
            tc = request.tc
        )
    }
}