package com.sc.mmusab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MMusabAuthUserToken {
  private String userName;
  private Set<String> roles;
  private String token;
}
