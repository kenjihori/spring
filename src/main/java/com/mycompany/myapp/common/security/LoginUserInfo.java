package com.mycompany.myapp.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mycompany.myapp.domain.model.MyAppUser;


public class LoginUserInfo extends User {

    private final MyAppUser myAppUser;

    public LoginUserInfo(MyAppUser myAppUser, Collection<GrantedAuthority> authorities) {
        super(myAppUser.getId(), myAppUser.getPassword(), true, true, true, true, authorities);
        this.myAppUser = myAppUser;
    }
    
    public String getId() {
        return myAppUser.getId();
    }
    public String getDepartmentCode() {
        return myAppUser.getDepartmentCode();
    }
    
}
