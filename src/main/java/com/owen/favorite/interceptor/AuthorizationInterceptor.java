package com.owen.favorite.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.owen.favorite.anno.Authorization;
import com.owen.favorite.constant.ApiConstant;
import com.owen.favorite.domain.APIResult;
import com.owen.favorite.service.TokenService;
import com.owen.favorite.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义拦截器，判断此次请求的用户是否已登录
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            // 如果不是映射到方法直接通过
            return true;
        }
        //从header中得到token
        String token = request.getHeader(ApiConstant.RequestParam.TOKEN);
        // 验证 token
        if (tokenService.checkToken(token)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(ApiConstant.RequestParam.USER_ID, TokenUtil.getUserIdFromToken(token));
            return true;
        } else {
            // 如果验证token失败，并且方法注明了Authorization，就告诉客户端token不对
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getMethodAnnotation(Authorization.class) != null) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

                PrintWriter writer = response.getWriter();
                writer.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(APIResult.createNg("请登录")));
                writer.close();
                return false;
            } else {
                return true;
            }
        }
    }
}
