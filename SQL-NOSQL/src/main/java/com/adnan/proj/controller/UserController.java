package com.adnan.proj.controller;

import com.adnan.proj.dto.*;

import com.adnan.proj.elastic.entity.CourseES;
import com.adnan.proj.elastic.entity.UserES;
import com.adnan.proj.entities.Course;
import com.adnan.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    SQL API's
    @PutMapping("/create")
    public String createUser(@RequestBody CreateUserDTO dto){
        return userService.createUser(dto);
    }

    @GetMapping("/getUser/{userName}")
    public GetUserDTO getUserByName(@PathVariable  String userName, String course){
        return userService.getUserByNameAndCourse(userName,  course);
    }

    @GetMapping("/getUsers")
    public UserListDTO getUserList(PageDTO dto){
        return userService.getUserList(dto);
    }

//    NoSQL API's
    @PutMapping("/createEUser")
    public UserES createEUser(@RequestBody CreateUserDTO dto){
        return userService.createESUser(dto);
    }

    @GetMapping("/getEUser/{name}")
    public GetEUserDTO getEUserByName(@PathVariable  String name){
        return userService.getESUserByName(name);
    }

    @GetMapping("/getEUsers")
    public UserListDTO getEUserList(PageDTO dto){
        return userService.getESUserList(dto);
    }

    @GetMapping("/getECourse")
    public GetCourseDTO getECourse(@RequestParam String courseName, @RequestParam(required = false) String id){
        return userService.getCourse(courseName, id);
    }

    @PutMapping("/createECourse")
    public CourseES createECourse(@RequestBody GetCourseDTO dto){
        return userService.createESCourse(dto);
    }

    @GetMapping("/getECourses")
    public CourseEsListDTO getECourseList(PageDTO dto){
        return userService.getESCourses(dto);
    }
}
