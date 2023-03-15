package com.adnan.proj.elastic.repo;

import com.adnan.proj.elastic.entity.UserES;
import com.adnan.proj.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserESRepo extends ElasticsearchRepository<UserES, Long> {
    UserES findByName(String userName);

    List<UserES> findBy(Boolean active);

    List<UserES> findByIsActive(boolean b);

//    @Query("SELECT new User(e.name, e.email, e.course.courseName) FROM User e")
//    List<UserES> findByQuery();
}
