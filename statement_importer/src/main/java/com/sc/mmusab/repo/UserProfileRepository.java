package com.sc.mmusab.repo;

import com.sc.mmusab.entity.auth.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
  List<UserProfile> findByUserName(String userName);
}