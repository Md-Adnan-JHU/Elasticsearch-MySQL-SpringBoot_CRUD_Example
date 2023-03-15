package com.adnan.proj.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserListDTO {

    private List<GetUserDTO> users;

    private Long totalUsers;

    private Integer totalPages;
}
