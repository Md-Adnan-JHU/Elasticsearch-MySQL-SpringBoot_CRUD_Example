package com.adnan.proj.dto;

import lombok.Data;

@Data
public class GetUserDTO {

    private Long id;

    private String name;

    private String email;

    private String course;

    private Integer duration;
}
