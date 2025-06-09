package com.sc.mmusab.dto;

import com.sc.mmusab.entity.auth.UserProfile;
import com.sc.mmusab.entity.auth.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MMusabUserDetails implements UserDetails {
  private final UserProfile userProfile;
  private final Set<UserRole> userRoles;

  public MMusabUserDetails(UserProfile userProfile, Set<UserRole> userRoles) {
    this.userProfile = userProfile;
    this.userRoles = userRoles;
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return userProfile.getUserPassword();
  }

  @Override
  public String getUsername() {
    return userProfile.getUserName();
  }
}
