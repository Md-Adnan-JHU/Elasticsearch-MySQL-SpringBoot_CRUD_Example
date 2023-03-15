package com.adnan.proj.service;

import com.adnan.proj.dto.*;
import com.adnan.proj.elastic.entity.CourseES;
import com.adnan.proj.elastic.entity.UserES;


public interface UserService {
    String createUser(CreateUserDTO dto);

    GetUserDTO getUserByNameAndCourse(String userName, String course);

    UserListDTO getUserList(PageDTO dto);

    UserES createESUser(CreateUserDTO dto);

    GetEUserDTO getESUserByName(String userName);

    UserListDTO getESUserList(PageDTO dto);

    GetCourseDTO getCourse(String courseName, String id);

     CourseES createESCourse(GetCourseDTO dto);

     CourseEsListDTO getESCourses(PageDTO dto) ;
}
