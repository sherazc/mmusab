package com.sc.mmusab;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHelperTest {
  @Test
  void testEncryptPassword() {
    String password = "password";
    System.out.println(new BCryptPasswordEncoder().encode(password));
  }
}
