package com.bit.springblogdemo.common.interceptor;

import com.bit.springblogdemo.common.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取字符串
        String userToken = request.getHeader("User-Token");

        Claims claims= JWTUtil.parseToken(userToken);

        if (claims != null) {
            // 校验通过，可以把用户信息存入 ThreadLocal (可选)，然后放行
            return true;
        }

        // 3. 校验失败，设置状态码为 401 (未授权) 并拦截
        response.setStatus(401);
        return false;

    }
}
