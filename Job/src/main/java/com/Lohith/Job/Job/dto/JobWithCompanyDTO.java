package com.Lohith.Job.Job.dto;

import com.Lohith.Job.Job.Job;
import com.Lohith.Job.Job.external.Company;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
