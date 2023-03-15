package com.adnan.proj.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetCourseDTO {

    private String id;

    private Boolean isActive;

    private String name;

    private Integer duration;

    private Date startDate;

    private Date endDate;
}
