package com.Lohith.Job.Job.dto;

import com.Lohith.Job.Job.Job;
import com.Lohith.Job.Job.external.Company;
import com.Lohith.Job.Job.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class JobDTO {
    private Job job;
    private Company company;
    private List<Review> reviews;
}
