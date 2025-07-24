package com.Lohith.Job.Job;

import com.Lohith.Job.Job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {

    List<JobWithCompanyDTO> findAll();
    Job findJobById(Long id);
    void  save(Job job);
    boolean Update(Long id,Job job);
    boolean delete(Long id);
}
