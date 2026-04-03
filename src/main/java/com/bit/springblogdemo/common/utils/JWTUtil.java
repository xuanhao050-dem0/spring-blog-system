package com.bit.springblogdemo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtil {

    //method1:自定义字符串，转化为字节数组
    public static final String s="sfanosifjadvklafdsafadfadf4svndpgfaiefkd";
    public static SecretKey key= Keys.hmacShaKeyFor(s.getBytes(StandardCharsets.UTF_8));

    //token过期时间
    public static final long expirationTime = 1 * 60 * 60 * 1000;




    /**
     * 生成token
     */

    public static String generateToken(Map<String,Object> claim){

      return Jwts.builder()
                .setClaims(claim) //传入用户信息
                .setIssuedAt(new Date()) //设置创建时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) //设置过期时间
                .signWith(key) //签名
                .compact();

    }



    public static Claims parseToken(String token) {

        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims body=null;
        try{
            body = build.parseClaimsJws(token).getBody();
        }catch (Exception e){
            log.info("token 不合法，token：{} ",token);
        }
        return body;

    }
}
