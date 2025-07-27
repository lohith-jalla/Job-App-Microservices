package com.Lohith.Company.Company;


import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean UpdateCompany(Long id,Company company);
    void CreateCompany(Company company);
    List<Long> getAllJobsByCompanyId(Long id);
    Company getCompanyById(Long id);
    boolean deleteCompany(Long id);
    void save(Company company);
}
