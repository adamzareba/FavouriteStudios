package models

case class FavouriteStudio(userId: Long, studioId: Long)

case class Company(id: Option[Long], name: String)

case class Address(id: Option[Long], zipCode: String, city: String, street: String, houseNumber: String)

case class Car(id: Option[Long], registrationNumber: String, companyId: Long)

case class Department(id: Option[Long], name: String, companyId: Long)

class User(id: Option[Long], name: String, surname: String, addressId: Long)

case class Employee(id: Option[Long], name: String, surname: String, addressId: Long, departmentId: Long)
  extends User(id, name, surname, addressId)

case class Office(id: Option[Long], name: String, addressId: Long, departmentId: Long)
