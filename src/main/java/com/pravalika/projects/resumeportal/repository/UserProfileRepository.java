package com.pravalika.projects.resumeportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pravalika.projects.resumeportal.models.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
  Optional<UserProfile> findByUserName(String userName);
}
