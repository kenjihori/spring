package com.mycompany.myapp.common.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.model.MyAppUser;
import com.mycompany.myapp.service.MyAppUserService;

@Component
@Service
public class LoginComponent implements UserDetailsService {

    @Autowired
    private MyAppUserService myAppUserService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyAppUser myAppUser = myAppUserService.findMyAppUser(username);
        if (myAppUser == null) {
            throw new UsernameNotFoundException("MyAppUser ID " + username + " Not Found");
        }
        
        // 拡張項目を利用するためのクラスを利用
        //return new LoginUserInfo(myAppUser, getAuthorities(myAppUser));
        return new LoginUserInfo(myAppUser, AuthorityUtils.NO_AUTHORITIES);

        // Clerkテーブル以外の付随情報がない場合はSpring Frameworkのクラスを利用
        // return new User(clerk.getId().toString(), clerk.getPassword(), true,
        // true, true, true, getAuthorities(clerk));

    }

    private Collection<GrantedAuthority> getAuthorities(MyAppUser myAppUser) {
        if (myAppUser.isAdmin()) {
            return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    }

}
