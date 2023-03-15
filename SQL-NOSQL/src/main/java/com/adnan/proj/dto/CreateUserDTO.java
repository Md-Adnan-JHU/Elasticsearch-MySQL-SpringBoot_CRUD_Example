package com.adnan.proj.dto;

import com.adnan.proj.entities.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserDTO {

    private String name;

    private String phone;

    private String email;

    private String address;

    private Gender gender;

    private String courseName;

    private Integer duration;

    private Boolean isActive;

    private Date startDate;

//    private Date endDate;
}
