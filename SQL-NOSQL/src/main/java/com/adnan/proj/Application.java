package com.adnan.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories("com.adnan.proj.elastic.repo")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);;
	}

}
