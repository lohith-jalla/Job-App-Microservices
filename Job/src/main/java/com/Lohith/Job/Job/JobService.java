package com.Lohith.Job.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    Job findJobById(Long id);
    void  save(Job job);
    boolean Update(Long id,Job job);
    boolean delete(Long id);
}
