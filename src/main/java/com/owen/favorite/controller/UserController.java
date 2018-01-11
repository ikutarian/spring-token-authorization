package com.owen.favorite.controller;

import com.owen.favorite.anno.Authorization;
import com.owen.favorite.constant.ApiConstant;
import com.owen.favorite.domain.APIResult;
import com.owen.favorite.domain.Token;
import com.owen.favorite.domain.User;
import com.owen.favorite.service.TokenService;
import com.owen.favorite.service.UserService;
import com.owen.favorite.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/add")
    public APIResult addToken(@RequestParam("user_id") long userId) {
        Token token = tokenService.createToken(userId);
        return APIResult.createOk(token);
    }

    @RequestMapping("/check")
    public APIResult getToken(@RequestParam String token) {
        boolean isValid = tokenService.checkToken(token);
        return APIResult.createOk(isValid);
    }

    @RequestMapping("/delete")
    public APIResult deleteToken(@RequestParam String token) {
        Long userId = TokenUtil.getUserIdFromToken(token);
        if (userId == null) {
            return APIResult.createNg("退出失败");
        }
        tokenService.deleteToken(userId);
        return APIResult.createOKMessage("退出成功");
    }

    @PostMapping("/login")
        public APIResult login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user == null /* 未注册 */ || !user.getPassword().equals(password) /* 密码错误 */) {
            return APIResult.createNg("用户名或密码错误");
        }
        //生成一个token，保存用户登录状态
        Token token = tokenService.createToken(user.getId());
        return APIResult.createOk(token);
    }

    @PostMapping("/logout")
    public APIResult logout(@RequestParam String token) {
        Long userId = TokenUtil.getUserIdFromToken(token);
        if (userId == null) {
            return APIResult.createNg("退出失败");
        }
        tokenService.deleteToken(userId);
        return APIResult.createOKMessage("退出成功");
    }

    /**
     * 测试一下token合法性
     */
    @Authorization
    @GetMapping("/shouldLogin")
    public APIResult shouldLogin(HttpServletRequest request) {
        return APIResult.createOk(request.getParameter(ApiConstant.RequestParam.USER_ID));
    }
}
