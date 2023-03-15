package com.adnan.proj.elastic.entity;

import com.adnan.proj.entities.Course;
import com.adnan.proj.entities.Gender;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Document(indexName = "user",  createIndex = true)
@Data
public class UserES {

    @Id
    @Field(fielddata = true,type = FieldType.Text)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field(type = FieldType.Text,fielddata = true,  store = true)
    private String name;

    @Field(type = FieldType.Text,fielddata = true,  store = true)
    private String phone;

    @Field(type = FieldType.Text,fielddata = true, store = true)
    private String email;

    @Field(type = FieldType.Text,fielddata = true,  store = true)
    private String address;

    @Field(type = FieldType.Text,fielddata = true,  store = true)
    private Gender gender;

    @Field(type = FieldType.Boolean,fielddata = true,  store = true)
    private Boolean isActive;

    @Field(type = FieldType.Nested,includeInParent = true,fielddata = true, store = true)
    private Course coursees;
}
