package services

import javax.inject.Inject

import models.Company
import repositories.CompanyRepository

import scala.concurrent.Future

trait CompanyService {

  def find(id: Long): Future[Company]

  def findAll: Future[List[Company]]

  def create(company: Company)

  def update(company: Company)

  def delete(id: Long)
}

class CompanyServiceImpl @Inject()(companyRepository: CompanyRepository) extends CompanyService {

  override def find(id: Long): Future[Company] = {
    companyRepository.find(id)
  }

  override def findAll: Future[List[Company]] = {
    companyRepository.findAll
  }

  override def create(company: Company) = {
    companyRepository.create(company)
  }

  override def update(company: Company) = {
    companyRepository.update(company.id.get, company)
  }

  override def delete(id: Long) = {
    companyRepository.delete(id)
  }
}
