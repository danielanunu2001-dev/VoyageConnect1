package com.travelagency.voyageconnect1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.expiration.ms}")
    private int jwtExpirationMs;

    @Value("${jwt.refresh.expiration.ms}")
    private int refreshTokenExpirationMs;

    private final RsaKeyGenerator rsaKeyGenerator;

    public JwtUtils(RsaKeyGenerator rsaKeyGenerator) {
        this.rsaKeyGenerator = rsaKeyGenerator;
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setHeaderParam("kid", "travel-2024")
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(rsaKeyGenerator.getPrivateKey())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(rsaKeyGenerator.getPublicKey()).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(rsaKeyGenerator.getPublicKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // Log exception
        }
        return false;
    }
}
