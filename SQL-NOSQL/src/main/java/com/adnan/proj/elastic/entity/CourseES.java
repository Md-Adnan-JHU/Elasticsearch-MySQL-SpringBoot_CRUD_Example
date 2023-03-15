package com.adnan.proj.elastic.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Document(indexName = "course",  createIndex = true)
@Data
public class CourseES {

    @Id
    @Field(fielddata = true, type = FieldType.Text)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field(type = FieldType.Text, fielddata = true, store = true)
    private String courseName;

    @Field(type = FieldType.Text, fielddata = true, store = true)
    private Integer duration;

    @Field(type = FieldType.Boolean, fielddata = true, store = true)
    private Boolean isActive;

    @Field(type = FieldType.Date, fielddata = true, store = true)
    private Date startDate;

    @Field(type = FieldType.Date, fielddata = true, store = true)
    private Date endDate;
}
