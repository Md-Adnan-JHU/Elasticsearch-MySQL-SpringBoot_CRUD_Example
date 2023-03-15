package com.adnan.proj.repository;

import com.adnan.proj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String userName);

    User findByNameAndCourse(String userName, String course);
}
