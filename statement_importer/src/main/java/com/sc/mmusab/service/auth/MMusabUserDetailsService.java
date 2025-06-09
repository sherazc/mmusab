package com.sc.mmusab.service.auth;

import com.sc.mmusab.dto.MMusabUserDetails;
import com.sc.mmusab.entity.auth.UserProfile;
import com.sc.mmusab.entity.auth.UserRole;
import com.sc.mmusab.repo.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MMusabUserDetailsService implements UserDetailsService {

  private final UserProfileRepository userProfileRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<UserProfile> users = userProfileRepository.findByUserName(username);
    if  (!users.isEmpty()) {
      UserProfile userProfile = users.get(0);
      Set<UserRole> userRoles = userProfile.getUserRoles();
      return new MMusabUserDetails(userProfile, userRoles);
    }
    throw new UsernameNotFoundException(username);
  }
}
