package com.fielamigo.app.FielAmigo.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fielamigo.app.FielAmigo.entity.FaUserDetails;

public class JwtUtil {

    public static final String JWT_SECRET = "FielAmigoApp2-2022IngSoft";

    /**
     * Generates a JWT token for the user
     * @param userId the user id
     * @param userDetails the details of the user
     * @param roles the roles of the user
     * @param expirationTimeInSeconds the expiration time in seconds
     * @return the JWT token
     */
    public static String generateToken(int userId, FaUserDetails userDetails, List<String> roles,
        int expirationTimeInSeconds, boolean isOwner) {
        String result;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            String token;
            if(userDetails != null) {
                token = JWT.create()
                .withIssuer("fielamigo")
                .withSubject(String.valueOf(userId))
                .withClaim("userId", userId)
                .withClaim("firstName", userDetails.getFirstName())
                .withClaim("lastName", userDetails.getLastName())
                .withClaim("isOwner", isOwner)
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeInSeconds * 1000))
                .sign(algorithm);
            } else {
                token = JWT.create()
                .withIssuer("fielamigo")
                .withSubject(String.valueOf(userId))
                .withClaim("userId", userId)
                .withClaim("isOwner", isOwner)
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

    /**
     * Returns the userId from the token
     * @param jwt the token
     * @return the userId
     * @throws UnauthorizedException if the token is invalid
     */
    public static int getUserIdFromToken(String jwt) throws UnauthorizedException {
        int userId;
        try {
            userId = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build()
                .verify(jwt)
                .getClaim("userId")
                .asInt();
        } catch (JWTVerificationException e) {
            throw new UnauthorizedException("Error verifying token");
        }
        return userId;
    }

    /**
     * Returns the token in the Authorization header
     * @param headers the headers of the request
     * @return the token
     */
    public static String getTokenFromHeader(Map<String, String> headers) throws UnauthorizedException {
        if(headers.get("Authorization") == null &&
            headers.get("authorization") == null) {
                throw new UnauthorizedException("No token provided");
        }

        String jwt;
        if(headers.get("Authorization") != null) {
            jwt = headers.get("Authorization").split(" ")[1];
        } else {
            jwt = headers.get("authorization").split(" ")[1];
        }

        if(jwt == null || jwt.isEmpty()) {
            throw new UnauthorizedException("Missing token.");
        }

        return jwt;
    }
}
