package com.directrice.user.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenGenerators {

    private static String Token="rohan";


    public String generateToken(UUID id) {

        Algorithm algorithm = null;

        try {
            algorithm = Algorithm.HMAC256(Token);
        } catch (IllegalArgumentException  e) {

            e.printStackTrace();
        }


        String token = JWT.create()
                            .withClaim("ID", String.valueOf(id))
                //token active for 5 minutes
                .withExpiresAt(Date.from(Instant.now().plusSeconds(300)))
                        .sign(algorithm);

        return token;
    }

    public UUID decodeToken(String token) {

        System.out.println(token);
        UUID userid;
//here verify the given token's algorithm

        Verification verification = JWT.require(Algorithm.HMAC256(Token));

        JWTVerifier jwtverifier = verification.build();
        DecodedJWT decodedjwt = jwtverifier.verify(token);
        Claim claim = decodedjwt.getClaim("ID");
        userid = UUID.fromString(claim.asString());
        System.out.println(userid);
        return userid;
    }
}
