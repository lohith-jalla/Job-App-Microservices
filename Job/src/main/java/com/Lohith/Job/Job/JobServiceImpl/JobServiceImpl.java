package com.Lohith.Job.Job.JobServiceImpl;


import com.Lohith.Job.Job.Job;
import com.Lohith.Job.Job.JobService;
import com.Lohith.Job.Job.JobRepository;
import com.Lohith.Job.Job.dto.JobWithCompanyDTO;
import com.Lohith.Job.Job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        // for find all we need to show not only the companyID at the api endpoint
        // but also the company details. so we called the company MicroService
        // and created a dto and then exposed it at the endpoint.
        List<Job> jobs=jobRepository.findAll();


        return jobs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobWithCompanyDTO convertToDTO(Job job){

        RestTemplate restTemplate = new RestTemplate();
        JobWithCompanyDTO jobWithCompanyDTO=new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        Company company = restTemplate.getForObject(
                "http://localhost:8081/companies/"+job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean Update(Long id,Job job) {
        Job jobToUpdate = jobRepository.findById(id).orElse(null);
        if (jobToUpdate != null) {
            jobToUpdate.setName(job.getName());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setStatus(job.getStatus());
            jobToUpdate.setLocation(job.getLocation());
            jobToUpdate.setCompanyId(job.getCompanyId());

            jobRepository.save(jobToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Job jobToDelete = jobRepository.findById(id).orElse(null);
        if(jobToDelete != null) {
            try {
                jobRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
