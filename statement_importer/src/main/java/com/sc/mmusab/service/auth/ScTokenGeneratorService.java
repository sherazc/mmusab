package com.sc.mmusab.service.auth;

import com.sc.mmusab.config.ScUserDetail;
import com.sc.mmusab.dto.ScToken;
import com.sc.mmusab.entity.auth.ScUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScTokenGeneratorService {

    private final JwtEncoder encoder;

    public ScTokenGeneratorService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public ScToken generateToken(Authentication authentication, String[] requestedScopes) {
        Instant now = Instant.now();
        List<String> requestedScopeList = List.of(requestedScopes);
        ScUserDetail scUserDetail = (ScUserDetail) authentication.getPrincipal();
        ScUser user = scUserDetail.getScUser();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(requestedScopeList::contains) // Add only the requested scopes
                .collect(Collectors.joining(" "));

        Instant expiration = now.plus(1, ChronoUnit.HOURS);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);

        String token = this.encoder.encode(encoderParameters).getTokenValue();

        return new ScToken(
            token,
            user.getUserName(),
            scope,
            LocalDateTime.ofInstant(now, ZoneId.systemDefault()),
            LocalDateTime.ofInstant(expiration, ZoneId.systemDefault())
        );
    }
}
