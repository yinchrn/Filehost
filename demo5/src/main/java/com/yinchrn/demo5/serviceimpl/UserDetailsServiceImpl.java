package com.yinchrn.demo5.serviceimpl;

import com.yinchrn.demo5.mapper.UserMapper;
import com.yinchrn.demo5.pojo.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    UserMapper userMapper;
    @Autowired
    UserDetailsServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount =  userMapper.findByUsername(username);
        if(Objects.isNull(userAccount)) throw new UsernameNotFoundException("用户不存在");
        List<GrantedAuthority> u = AuthorityUtils.createAuthorityList("ROLE_user");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //使用 PasswordEncoderFactories.createDelegatingPasswordEncoder(); 会在加密后的字符串前加{加密器类型}
        //如果在 config 中提前指定了加密类型，则不需要再加{类型}，直接使用 BCryptPasswordEncoder 加密 就行
        return new User(userAccount.getUsername(),passwordEncoder.encode(userAccount.getPassword()), u);
    }
}
