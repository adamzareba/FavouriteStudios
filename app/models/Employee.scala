package models

class Employee(id: Long, name: String, surname: String, address: Address, department: Department)
  extends User(id, name, surname, address)