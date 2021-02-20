//package com.linhao.demo.test.security.配置相关;
//
//import com.linhao.demo.test.security.从数据库查询用户验证.FavUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////@Configuration用于定义配置类，可替换xml配置文件，
//// 被注解的类内部包含有一个或多个被@Bean注解的方法，
//// 这些方法将会被AnnotationConfigApplicationContext
//// 或AnnotationConfigWebApplicationContext类进行扫描，
//// 并用于构建bean定义，初始化Spring容器。
//
////要求：：
////@Configuration不可以是final类型；
////@Configuration不可以是匿名类；
////嵌套的configuration必须是静态类。
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    FavUserDetailService favUserDetailService;
//
//    //从 Spring5 开始，强制要求密码要加密
//    @Override
//// 身份验证管理器生成器
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("admin")
////                .password(new BCryptPasswordEncoder().encode("123"))
////                .roles("ADMIN");
//        // 如果是连接数据库查询的话使用这行代码，
//        // 将userDetailsService改为自己的就可以了。
//
//
//        auth.userDetailsService(favUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
//
//    }
//
//
//    //不加就会报错There is no PasswordEncoder mapped for the id "null"
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //对 swagger-ui.html 放行
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/swagger-ui.html");
//    }
//
//
//}