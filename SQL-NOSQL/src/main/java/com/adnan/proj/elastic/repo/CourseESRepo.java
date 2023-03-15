package com.adnan.proj.elastic.repo;

import com.adnan.proj.elastic.entity.CourseES;
import com.adnan.proj.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseESRepo extends PagingAndSortingRepository<CourseES, String>, ElasticsearchRepository<CourseES, String> {
    CourseES findByCourseName(String courseName);

    Page<Course> findByIsActive(boolean b, Pageable pageable);
}
