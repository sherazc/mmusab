package com.sc.mmusab.config;

import com.sc.mmusab.entity.auth.ScUser;
import com.sc.mmusab.repo.ScUserRepository;
import com.sc.mmusab.repo.ScUserRoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScUserDetailServiceImpl implements UserDetailsService {

    private final ScUserRepository scUserRepository;
    private final ScUserRoleRepository scUserRoleRepository;

    public ScUserDetailServiceImpl(ScUserRepository scUserRepository, ScUserRoleRepository scUserRoleRepository) {
        this.scUserRepository = scUserRepository;
        this.scUserRoleRepository = scUserRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ScUser> scUser = scUserRepository.findByUserNameIgnoreCase(username);

        List<String> scRolesName = scUser.map(ScUser::getId)
                .map(scUserRoleRepository::findScRolesByScUserId)
                .orElse(List.of());

        return scUser
                .map(u -> new ScUserDetail(u, scRolesName))
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user: " + username));
    }
}
