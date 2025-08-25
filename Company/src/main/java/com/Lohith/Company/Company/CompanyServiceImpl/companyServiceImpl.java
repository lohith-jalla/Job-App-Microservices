package com.Lohith.Company.Company.CompanyServiceImpl;

import com.Lohith.Company.Company.CompanyService;
import com.Lohith.Company.Company.Company;
import com.Lohith.Company.Company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class companyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public companyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean UpdateCompany(Long id, Company company) {
        Company companyToUpdate=companyRepository.findById(id).orElse(null);
        if(companyToUpdate!=null){
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobId(company.getJobId());
            companyToUpdate.setDescription(company.getDescription());

            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean addJobToCompany(Long companyId, Long jobId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            return false;
        }

        List<Long> jobIds = company.getJobId();
        if (jobIds == null) {
            jobIds = new ArrayList<>();
        }

        if (!jobIds.contains(jobId)) {
            jobIds.add(jobId);
            company.setJobId(jobIds);
            companyRepository.save(company);
        }

        return true;
    }



    @Override
    public void CreateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Long> getAllJobsByCompanyId(Long id) {
        return companyRepository.findById(id).get().getJobId();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }
}
