package com.adnan.proj.serviceimpl;


import com.adnan.proj.dto.*;
import com.adnan.proj.elastic.entity.CourseES;
import com.adnan.proj.elastic.entity.UserES;
import com.adnan.proj.elastic.repo.CourseESRepo;
import com.adnan.proj.elastic.repo.UserESRepo;
import com.adnan.proj.entities.Course;
import com.adnan.proj.entities.User;
import com.adnan.proj.repository.CourseRepo;
import com.adnan.proj.repository.UserRepo;
import com.adnan.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserESRepo userESRepo;

    @Autowired
    private CourseESRepo courseESRepo;


    @Override
    public String createUser(CreateUserDTO dto){

        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setDuration(dto.getDuration());
        course.setStartDate(dto.getStartDate());
        courseRepo.save(course);

        User user = new User();

        user.setName(dto.getName());
        user.setCourse(course);
        user.setGender(dto.getGender());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        userRepo.save(user);

        return "Created Successfully!";
    }

    @Override
    public GetUserDTO getUserByNameAndCourse(String userName, String course){

        User user = userRepo.findByNameAndCourse(userName,course);

        if(user == null)
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User Name!");

        GetUserDTO userDTO = new GetUserDTO();
        userDTO.setDuration(user.getCourse().getDuration());
        userDTO.setName(user.getName());
        userDTO.setCourse(user.getCourse().getCourseName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());

        return userDTO;
    }

    @Override
    public UserListDTO getUserList(PageDTO dto){

        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), Sort.by(Sort.Order.desc("id")));

        Page<User> users = userRepo.findAll(pageable);

        List<GetUserDTO> userDTOList = new ArrayList<>();
        for(User user : users){
            GetUserDTO userDTO = new GetUserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            if(user.getCourse() != null)
                userDTO.setCourse(user.getCourse().getCourseName());

            userDTOList.add(userDTO);
        }

        UserListDTO listDTO = new UserListDTO();
        listDTO.setUsers(userDTOList);
        listDTO.setTotalUsers(users.getTotalElements());
        System.out.println(users.getTotalElements());
        return listDTO;
    }



    @Override
    public UserES createESUser(CreateUserDTO dto) {
//        CourseES course = new CourseES();
//        course.setId(dto.getId());
//        course.setCourseName(dto.getCourseName());
//        course.setDuration(dto.getDuration());
//        course.setStartDate(dto.getStartDate());
//        course.setEndDate(dto.getEndDate());
//        courseESRepo.save(course);

        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setDuration(dto.getDuration());
        courseRepo.save(course);

        UserES user = new UserES();
        user.setIsActive(dto.getIsActive());
        user.setName(dto.getName());
        user.setCoursees(course);
        user.setGender(dto.getGender());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        userESRepo.save(user);


        return user;
    }

    @Override
    public GetEUserDTO getESUserByName(String name) {

        UserES user = userESRepo.findByName(name);

        if(user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id!");

        GetEUserDTO userDTO = new GetEUserDTO();
        userDTO.setName(user.getName());
        userDTO.setId(user.getId());
        userDTO.setCourse(user.getCoursees().getCourseName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    @Override
    public UserListDTO getESUserList(PageDTO dto) {
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize());

        Page<UserES> users = userESRepo.findAll(pageable);

        List<GetUserDTO> userDTOList = new ArrayList<>();
        for(UserES user : users){
            GetUserDTO userDTO = new GetUserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            if(user.getCoursees() == null) {
                userDTO.setCourse(null);
            } else {
                userDTO.setCourse(user.getCoursees().getCourseName());
            }
            userDTOList.add(userDTO);
        }

        UserListDTO listDTO = new UserListDTO();
        listDTO.setUsers(userDTOList);
        listDTO.setTotalUsers(users.getTotalElements());
        listDTO.setTotalPages(users.getTotalPages());
        System.out.println(users.getTotalElements());

        return listDTO;
    }

    @Override
    public GetCourseDTO getCourse(String courseName, String id){

        CourseES courseES = courseESRepo.findByCourseName(courseName);

        if(courseES == null)
            courseES = courseESRepo.findById(id).orElse(null);

        if(courseES == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Course Name or Id!!");


        GetCourseDTO dto = new GetCourseDTO();
        dto.setName(courseES.getCourseName());
        dto.setId(courseES.getId());
        dto.setDuration(courseES.getDuration());
        dto.setEndDate(courseES.getEndDate());
        dto.setStartDate(courseES.getStartDate());

        return dto;
    }

    @Override
    public CourseES createESCourse(GetCourseDTO dto) {

        CourseES course = new CourseES();
        course.setIsActive(true);
        course.setCourseName(dto.getName());
        course.setDuration(dto.getDuration());
        course.setStartDate(dto.getStartDate());
        course.setEndDate(dto.getEndDate());
        courseESRepo.save(course);

        return course;
    }

    @Override
    public CourseEsListDTO getESCourses(PageDTO dto) {
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), Sort.by(Sort.Order.desc("id")));

        Page<CourseES> courseES = courseESRepo.findAll(pageable);



        List<GetCourseDTO> courses = new ArrayList<>();
        for(CourseES course : courseES){
            GetCourseDTO dto1 = new GetCourseDTO();
            dto1.setIsActive(course.getIsActive());
            dto1.setName(course.getCourseName());
            dto1.setDuration(course.getDuration());
            dto1.setStartDate(course.getStartDate());
            dto1.setEndDate(course.getEndDate());
            dto1.setId(course.getId());

            courses.add(dto1);
        }

        CourseEsListDTO listDTO = new CourseEsListDTO();
        listDTO.setCourses(courses);
        listDTO.setTotalElements(courseES.getTotalElements());
        listDTO.setTotalPages(courseES.getTotalPages());
        System.out.println(courseES.getTotalElements());

        return listDTO;

    }

}



