package com.sc.mmusab.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userName;

  private String userPassword;

  @ManyToMany
  @JoinTable(name = "M2M_USER_PROFILE_USER_ROLE",
      joinColumns = {@JoinColumn(name = "USER_PROFILE_ID")},
      inverseJoinColumns = {@JoinColumn(name = "USER_ROLE_ID")})
  private Set<UserRole> userRoles = new HashSet<>();

}
