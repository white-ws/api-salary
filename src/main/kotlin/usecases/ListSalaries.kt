package usecases

import datastores.SalaryDatastore
import entities.Salary

class ListSalaries(private val salaryDatastore: SalaryDatastore) {
    operator fun invoke(): List<Salary> {
        return salaryDatastore.getAll()
    }
}