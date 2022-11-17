package com.fielamigo.app.FielAmigo.utils;

import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class AuthUtil {
    
    /**
     * Verifies if the token is valid and returns the userId
     * @param token the token to verify
     * @return the userId
     * @throws FielAmigoException if the token is invalid
     */
    public static int isUserAuthenticated(String token) throws FielAmigoException {
        int subject;
        try {
            String subjectString = JWT.require(Algorithm.HMAC256(JwtUtil.JWT_SECRET))
                .build()
                .verify(token)
                .getSubject();

            subject = Integer.parseInt(subjectString);
        } catch (JWTVerificationException e) {
            throw new FielAmigoException("Error verifying token");
        }
        return subject;
    }

    public static void verifyHasRole(String jwt, String role) throws UnauthorizedException{
        List<String> roles = JWT.require(Algorithm.HMAC256(JwtUtil.JWT_SECRET))
            .build()
            .verify(jwt)
            .getClaim("roles")
            .asList(String.class);
        
        if(!roles.contains(role)) {
            throw new UnauthorizedException("User does not have the required role");
        }
    }
}
