package com.sc.mmusab.repo;

import com.sc.mmusab.entity.auth.ScUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScUserRepository extends CrudRepository<ScUser, Long> {
    Optional<ScUser> findByUserNameIgnoreCase(String userName);
}
