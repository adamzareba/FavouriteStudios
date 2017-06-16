package database

import java.sql.Timestamp
import java.time.{LocalDateTime, ZoneOffset}

import models._
import slick.driver.MySQLDriver.api._

trait DatabaseSchema {

  implicit val localDateTimeMapping = MappedColumnType.base[LocalDateTime, Timestamp](
    localDateTime => Timestamp.from(localDateTime.toInstant(ZoneOffset.UTC)),
    _.toLocalDateTime
  )

  class Companies(tag: Tag) extends Table[Company](tag, "COMPANY") {
    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def name = column[String]("NAME")
    def createdOn = column[LocalDateTime]("CREATED_ON")

    def * = (id.?, name, createdOn) <> (Company.tupled, Company.unapply)
  }

  val companies = TableQuery[Companies]

  class Addresses(tag: Tag) extends Table[Address](tag, "ADDRESS") {
    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def zipCode = column[String]("ZIP_CODE")
    def city = column[String]("CITY")
    def street = column[String]("STREET")
    def houseNumber = column[String]("HOUSE_NUMBER")

    def * = (id.?, zipCode, city, street, houseNumber) <> (Address.tupled, Address.unapply)
  }

  val addresses = TableQuery[Addresses]

  class Cars(tag: Tag) extends Table[Car](tag, "CAR") {
    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def registrationNumber = column[String]("REGISTRATION_NUMBER")
    def companyId = column[Long]("COMPANY_ID")

    def company = foreignKey("FK_CAR_COMPANY", companyId, companies)(_.id)

    def * = (id.?, registrationNumber, companyId) <> (Car.tupled, Car.unapply)
  }

  val cars = TableQuery[Cars]

  class Departments(tag: Tag) extends Table[Department](tag, "DEPARTMENT") {
    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def name = column[String]("NAME")
    def companyId = column[Long]("COMPANY_ID")

    def company = foreignKey("FK_DEPARTMENT_COMPANY", companyId, companies)(_.id)

    def * = (id.?, name, companyId) <> (Department.tupled, Department.unapply)
  }

  val departments = TableQuery[Departments]

  class Employees(tag: Tag) extends Table[Employee](tag, "EMPLOYEE") {
    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def name = column[String]("NAME")
    def surname = column[String]("SURNAME")
    def addressId = column[Long]("ADDRESS_ID")
    def departmentId = column[Long]("DEPARTMENT_ID")

    def address = foreignKey("FK_EMPLOYEE_ADDRESS", addressId, addresses)(_.id)
    def department = foreignKey("FK_EMPLOYEE_DEPARTMENT", departmentId, departments)(_.id)

    def * = (id.?, name, surname, addressId, departmentId) <> (Employee.tupled, Employee.unapply)
  }

  val employees = TableQuery[Employees]

  class Offices(tag: Tag) extends Table[Office](tag, "OFFICE") {
    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    def name = column[String]("NAME")
    def addressId = column[Long]("ADDRESS_ID")
    def departmentId = column[Long]("DEPARTMENT_ID")

    def address = foreignKey("FK_OFFICE_ADDRESS", addressId, addresses)(_.id)
    def department = foreignKey("FK_OFFICE_DEPARTMENT", departmentId, departments)(_.id, onUpdate = ForeignKeyAction.Restrict)

    def * = (id.?, name, addressId, departmentId) <> (Office.tupled, Office.unapply)
  }

  val offices = TableQuery[Offices]

  val allSchemas = companies.schema ++ addresses.schema ++ cars.schema ++ departments.schema ++ employees.schema ++ offices.schema
}
