package services

import javax.inject.Inject

import models.Company
import repositories.CompanyRepository

trait CompanyService {

  def find(id: Long): Company

  def findAll: List[Company]

  def create(company: Company)

  def update(company: Company)

  def delete(id: Long)
}

class CompanyServiceImpl @Inject()(companyRepository: CompanyRepository) extends CompanyService {

  override def find(id: Long): Company = {
    companyRepository.find(id)
  }

  override def findAll: List[Company] = {
    companyRepository.findAll
  }

  override def create(company: Company) = {
    companyRepository.create(company)
  }

  override def update(company: Company) = {
    companyRepository.update(company)
  }

  override def delete(id: Long) = {
    companyRepository.delete(id)
  }
}
