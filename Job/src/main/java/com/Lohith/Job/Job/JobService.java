package com.Lohith.Job.Job;

import com.Lohith.Job.Job.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();
    Job findJobById(Long id);
    void  save(Job job);
    boolean Update(Long id,Job job);
    boolean delete(Long id);
}
