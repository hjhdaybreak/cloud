package com.bee.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JWTUtils {

    // 秘钥
    public static final String SECRET_KEY = "erbadagang-123456";
    // token 过期时间
    public static final long TOKEN_EXPIRE_TIME = 5 * 60 * 1000;
    // 签发人
    private static final String ISSUER = "issuer";
    // 用户名
    private static final String USER_NAME = "username";


    public static String token(String username) {
        Date now = new Date();
        //SECRET_KEY是用来加密数据签名秘钥
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                // 签发人
                .withIssuer(ISSUER)
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                // 保存权限标记
                .withClaim(USER_NAME, username)
                .sign(algorithm);


        log.info("jwt generated user={}", username);
        return token;
    }

    public static boolean verify(String token) {
        try {
            //SECRET_KEY是用来加密数据签名秘钥
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            //如果校验有问题会抛出异常。
            verifier.verify(token);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String token = token(USER_NAME);
        System.out.println(token);

        String t = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJpc3N1ZXIiLCJleHAiOjE2Njc1NDgwNjgsImlhdCI6MTY2NzU0Nzc2OCwidXNlcm5hbWUiOiJ1c2VybmFtZSJ9._JO938odaMX3rDBvLuI44WFoPrC7b32QZef_riElLls";
        boolean verify = verify(t);
        System.out.println(verify);
    }
}
