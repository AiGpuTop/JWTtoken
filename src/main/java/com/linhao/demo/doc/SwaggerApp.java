package com.linhao.demo.doc;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

//swagger2的配置文件，在项目的启动类的同级文件建立
@Configuration
@EnableSwagger2
//开启EnableKnife4j UI，这个没啥用就是是更改一个页面
//@EnableKnife4j
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
//@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
public class SwaggerApp {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean("质量管理")
    public Docket controller1Apis() {
        //    设置携带token
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name(AUTHORIZATION_HEADER).description("user ticket")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数


        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("质量管理")
                .apiInfo(apiInfo1())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.linhao.demo.test"))
                .paths(PathSelectors.any())//对所有的进行管理
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                // swagger分组配置API时，可以使用路径选择器来选择不同分组的API
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
    }

    //应该是返回一个list身份  新建了一个全局的管理员身份
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }


    @Bean("默认")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("默认")
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.linhao.demo.test"))
                .paths(PathSelectors.any())//对所有的进行管理
//                .paths(Predicates.not(PathSelectors.regex("/index334")))//错误路径不监控
//               .apis(RequestHandlerSelectors.any())// 对所有api进行监控
////            .apis(RequestHandlerSelectors.basePackage("com.hanstrovsky.controller"))// 选择监控的package
////              .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))// 只监控有ApiOperation注解的接口
//                //不显示错误的接口地址
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
//                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                .build();
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 使用 Swagger2 构建RESTful API 我就是是是是是是是")
                //创建人
                .contact(new Contact("林昊", "http://www.baidu.com", "aaroma@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述 测试问答")
                .build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 使用 Swagger2 质量管理")
                //创建人
                .contact(new Contact("linhaossssssssssssssssssssssss", "http://www.baidu.com", "aaroma@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述 测试问答")
                .build();
    }
}