package com.linhao.demo.test.security;

import com.linhao.demo.test.jwt.JWTtoken工具类实现创建token与校验token功能.JwtTokenUtils;
import com.linhao.demo.test.jwt.定义认证失败处理类.JwtAuthenticationEntryPoint;
import com.linhao.demo.test.jwt.实现token验证的过滤器.JwtAuthenticationTokenFilter;
import com.linhao.demo.test.jwt.无权限访问类处理.JwtAccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 *
 * @author zhuhuix
 * @date 2020-03-25
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenUtils jwtTokenUtils;

    public WebSecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtTokenUtils jwtTokenUtils) {

        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtTokenUtils = jwtTokenUtils;

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // 禁用 CSRF
                .csrf().disable()

                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 防止iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()

                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                // 放行静态资源
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/webSocket/**"
                ).permitAll()

                // 放行swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()

                // 放行文件访问
                .antMatchers("/file/**").permitAll()

                // 放行druid
                .antMatchers("/druid/**").permitAll()

                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                //允许匿名及登录用户访问
                .antMatchers("/api/auth/**", "/error/**").permitAll()
                // 所有请求都需要认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity
                .apply(new TokenConfigurer(jwtTokenUtils));

    }

    public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private final JwtTokenUtils jwtTokenUtils;

        public TokenConfigurer(JwtTokenUtils jwtTokenUtils){

            this.jwtTokenUtils = jwtTokenUtils;
        }

        @Override
        public void configure(HttpSecurity http) {
            JwtAuthenticationTokenFilter customFilter = new JwtAuthenticationTokenFilter(jwtTokenUtils);
            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

}

