package com.bit.springblogdemo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    //method1:自定义字符串，转化为字节数组
    public static final String s="sfanosifjadvklafdsafadfadf4svndpgfaiefkd";
    SecretKey key= Keys.hmacShaKeyFor(s.getBytes(StandardCharsets.UTF_8));

    //token过期时间
    public static final long expirationTime = 1 * 60 * 60 * 1000;




    /**
     * 生成token
     */
    @Test
    public void generateToken(){

        //用户信息
        Map<String,Object> claim=new HashMap<>();
        claim.put("id",1);
        claim.put("name","zhangsan");

        //method2:secretKeyFor生成
        SecretKey key1 = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String compact = Jwts.builder()
                .setClaims(claim) //传入用户信息
                .setIssuedAt(new Date()) //设置创建时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) //设置过期时间
                .signWith(key) //签名
                .compact();

        System.out.println(compact);

    }


    @Test
    public void parseToken(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbmdzYW4iLCJpZCI6MSwiaWF0IjoxNzc1MjIzNzQ4LCJleHAiOjE3NzUyMjczNDh9.Z2bU6D-c_l4oQ3dLDUUzFJGlsXCnnjMYvCDmvhEnCes";
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims body = build.parseClaimsJws(token).getBody();
        System.out.println(body);


    }

}
