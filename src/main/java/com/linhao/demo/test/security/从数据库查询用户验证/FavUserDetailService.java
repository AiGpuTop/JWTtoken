package com.linhao.demo.test.security.从数据库查询用户验证;

import com.linhao.demo.test.jpa.repository.RoleRepository;
import com.linhao.demo.test.jpa.repository.UserRepository;
import com.linhao.demo.test.model.Role;
import com.linhao.demo.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
//UserDetailsService 用户详细信息服务
public class FavUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        User user =  userRepository.findByName(username);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }


        // 存放权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Map map = new HashMap();
        map.put("username",username);
        List<Role> roles = roleRepository.findByRoleName(username);
        // 查询权限
        for (Role role:roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        // 重点看这个哦new BCryptPasswordEncoder().encode(user.getPassword())
        return new org.springframework.security.core.userdetails
                .User(username,new BCryptPasswordEncoder().encode(user.getPassword()),new ArrayList<>());
    }
}
