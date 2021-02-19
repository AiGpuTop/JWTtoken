package com.linhao.demo.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Api("用户操作接口")
@Controller("用户操作接口")
@RequestMapping("/")
public class testIt {

    @GetMapping({"", "/index"})
    @ResponseBody
    @ApiOperation(value = "根据id查询商品", notes = "查询数据库中某个的商品信息")
    public String sdasad() {
        return "dsijhsdaijnsadjknojinioii";
    }

    @GetMapping({"/index334"})
    @ResponseBody
    @ApiOperation(value = "api的名称", notes = "查询数据库中某个的商品信息傻傻等四大")
//    paramType：表示参数放在哪个地方
//    header-->请求参数的获取：@RequestHeader(代码中接收注解)
//    query-->请求参数的获取：@RequestParam(代码中接收注解)
//    path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
//    body-->请求参数的获取：@RequestBody(代码中接收注解)
//    form（不常用）

//    参数可以这样写
//    @ApiImplicitParams({
//
//    })
    @ApiImplicitParam(name = "userName", value = "这里是用户名称", paramType = "query", required = true, dataType = "String")
    public String sdasa111d(String userName) {
        return "dsijhsdaijnsadjknojinioii" + userName;
    }


}
