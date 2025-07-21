package com.Lohith.Company.Company;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

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
            @PathVariable Long id
    ){
        Company company=companyService.getCompanyById(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/{id}/jobs")
//    public ResponseEntity<List<Long>> getJobsByCompanyId(
//            @PathVariable Long id
//    ){
//        List<Job> jobs=companyService.getAllJobsByCompanyId(id);
//        return new ResponseEntity<>(jobs, HttpStatus.OK);
//    }

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
