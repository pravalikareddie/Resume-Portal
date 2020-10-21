package com.pravalika.projects.resumeportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pravalika.projects.resumeportal.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.pravalika.projects.resumeportal.repository"})
public class ResumePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumePortalApplication.class, args);
	}

}
