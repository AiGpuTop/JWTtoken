package com.linhao.demo.test.jpa.controller;

import com.linhao.demo.test.model.Student;
import com.linhao.demo.test.jpa.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api("用户操作接口")
@RestController("/Student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("StudentController3")
    @ApiOperation(value = "api的名称", notes = "查询数据库中某个的商品信息傻傻等四大")
    public Student GetGoodList(@RequestParam("name") String name, @RequestParam(value = "age", required = false,
            defaultValue = "27") int age) throws Exception {

        Student hudsauhdshudsauhi = studentRepository.save(new Student().setAddress("").setAge(1).setCollage("11").setName("hudsauhdshudsauhi"));
        System.out.println(hudsauhdshudsauhi);
        Student user = studentRepository.findByNameAndAge(name, age);
         user = studentRepository.findByNameFoQuery(name);
        return user;
    }


    @GetMapping("/getById2")
    @ApiOperation(value = "根据id 获取", notes = "查询数据库中某个的商品信息傻傻等四大")
    @ApiImplicitParam(name = "age", value = "这里是用户id", paramType = "query", required = true, dataType = "String")
    public Student getById(@RequestParam(value = "age", required = true, defaultValue = "1") Long id) throws Exception {
        Optional<Student> byId = studentRepository.findById(id);
        Student student = byId.orElse(new Student().setName("窝里个去没有啊"));
        return student;
    }
}
