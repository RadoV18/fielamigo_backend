package com.fielamigo.app.FielAmigo.utils;

import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fielamigo.app.FielAmigo.entity.FaUserDetails;

public class JwtUtil {

    public static final String JWT_SECRET = "FielAmigoApp2-2022IngSoft";

    public static String generateToken(int userId, FaUserDetails userWithDetails, List<String> roles, int expirationTimeInSeconds) {
        String result;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            String token;
            if(userWithDetails != null) {
                token = JWT.create()
                .withIssuer("fielamigo")
                .withClaim("userId", userId)
                .withClaim("firstName", userWithDetails.getFirstName())
                .withClaim("lastName", userWithDetails.getLastName())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeInSeconds * 1000))
                .sign(algorithm);
            } else {
                token = JWT.create()
                .withIssuer("fielamigo")
                .withClaim("userId", userId)
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeInSeconds * 1000))
                .sign(algorithm);
            }
            result = token;
        } catch (FielAmigoException e) {
            throw new FielAmigoException("Error generating token", e);
        }
        return result;
    }
}
