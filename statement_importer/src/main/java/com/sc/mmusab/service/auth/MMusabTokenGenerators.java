package com.sc.mmusab.service.auth;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

@Service
public class MMusabTokenGenerators {
  private final JwtEncoder jwtEncoder;
  private final MMusabUserDetailsService userDetailsService;
}
