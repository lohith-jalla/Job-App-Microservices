package com.Lohith.Company.Company.client;

import com.Lohith.Company.Company.external.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Job")
public interface JobClient {
    @GetMapping("/jobs/{id}")
    Job getJobById(@PathVariable("id") Long id);
}
