package com.adnan.proj.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseEsListDTO {

    List<GetCourseDTO> courses;

    private Long totalElements;

    private Integer totalPages;
}
