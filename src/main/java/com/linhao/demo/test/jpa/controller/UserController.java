package com.linhao.demo.test.jpa.controller;

import com.linhao.demo.test.jpa.repository.UserRepository;
import com.linhao.demo.test.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api("用户密码验证操作接口")
@RestController("/User")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("UserController3")
    @ApiOperation(value = "api的名称", notes = "查询数据库中某个的商品信息傻傻等四大")
    public User GetGoodList(@RequestParam("name") String name, @RequestParam(value = "age", required = false,
            defaultValue = "27") int age) throws Exception {

        User hudsauhdshudsauhi = userRepository.save(new User().setName("hudsauhdshudsauhi"));
        System.out.println(hudsauhdshudsauhi);
//        User user = userRepository.findByNameAndAge(name, age);
        User  user = userRepository.findByNameFoQuery(name);
        return user;
    }


    @GetMapping("/getById4")
    @ApiOperation(value = "根据id 获取", notes = "查询数据库中某个的商品信息傻傻等四大")
    @ApiImplicitParam(name = "age", value = "这里是用户id", paramType = "query", required = true, dataType = "String")
    public User getById(@RequestParam(value = "age", required = true, defaultValue = "1") Long id) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        User User = byId.orElse(new User().setName("窝里个去没有啊"));
        return User;
    }
}
