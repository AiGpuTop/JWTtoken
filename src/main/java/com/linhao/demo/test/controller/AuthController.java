package com.linhao.demo.test.controller;

import com.linhao.demo.test.jwt.JWTtoken工具类实现创建token与校验token功能.JwtTokenUtils;
import com.linhao.demo.test.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "系统授权接口")
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;

    public AuthController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @ApiOperation("登录授权")
    @GetMapping(value = "/login")
    public JsonResult login(@RequestParam(value = "user", required = true, defaultValue = "admin")  String user,@RequestParam(value = "password", required = true, defaultValue = "password")  String password){
        Map map = new HashMap();
        map.put("user",user);
        map.put("password",password);
        String token = jwtTokenUtils.createToken(map);

        return new JsonResult(1,map,"哈哈，成功了！！！！！","Bearer "+token);
    }

    @ApiOperation("获取身份")
    @GetMapping(value = "/principal")
    public Object principal(String token){
        return jwtTokenUtils.getAuthentication(token).getPrincipal();
    }
}
