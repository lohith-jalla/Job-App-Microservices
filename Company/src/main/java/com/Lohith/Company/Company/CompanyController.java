package com.Lohith.Company.Company;



import com.Lohith.Company.Company.external.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    private RestTemplate restTemplate;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies=companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(
            @PathVariable("id") Long id
    ){
        Company company=companyService.getCompanyById(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<Job>> getJobsByCompanyId(
            @PathVariable("id") Long id
    ){
        List<Long> jobsId=companyService.getAllJobsByCompanyId(id);
        if(jobsId==null || jobsId.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        List<Job> jobs=jobsId.stream()
                .map(jobId-> restTemplate.getForObject("http://Job:8082/jobs/"+jobId,Job.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(
            @RequestBody Company company
    ){
        companyService.CreateCompany(company);
        return new ResponseEntity<>("Company created", HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateCompany(
            @PathVariable Long id,
            @RequestBody Company company
    ){
        boolean updated=companyService.UpdateCompany(id, company);
        if(updated){
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable Long id
    ){
        boolean deleted=companyService.deleteCompany(id);
        if(deleted){
            return new ResponseEntity<>("Company with id "+ id +" deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }


}
