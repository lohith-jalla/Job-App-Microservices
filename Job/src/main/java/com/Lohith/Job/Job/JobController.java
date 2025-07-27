package com.Lohith.Job.Job;

import com.Lohith.Job.Job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(
            @PathVariable Long id
    ){
        Job job=jobService.findJobById(id);
        if(job!=null){
            return ResponseEntity.ok(job);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> CreateJob(
            @RequestBody Job job
    ){
        jobService.save(job);

        return new ResponseEntity<>("Job Created Successfully !",HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateJob(
            @PathVariable Long id,
            @RequestBody Job job
    ){
        boolean updated = jobService.Update(id,job);
        if(updated)
            return ResponseEntity.ok("Job with id updated successfully !");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id
    ){
        boolean isDeleted=jobService.delete(id);
        if(isDeleted)
            return  ResponseEntity.ok("Job Deleted Successfully !");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
