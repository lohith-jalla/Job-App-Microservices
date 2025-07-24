package com.Lohith.Job.Job.external;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Company {

    private Long id;
    private String name;
    private String description;

}
