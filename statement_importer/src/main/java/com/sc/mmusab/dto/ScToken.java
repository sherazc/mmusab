package com.sc.mmusab.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ScToken(
    String token,
    String userName,
    String roles,
    LocalDateTime createdDateTime,
    LocalDateTime expiresDateTime) {
}
