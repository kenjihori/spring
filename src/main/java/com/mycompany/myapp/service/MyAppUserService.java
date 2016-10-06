package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.mapper.MyAppUserMapper;
import com.mycompany.myapp.domain.model.MyAppUser;

@Transactional
@Service
public class MyAppUserService {

    @Autowired
    MyAppUserMapper myAppUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    public MyAppUser findMyAppUser(String id) {
        return myAppUserMapper.findOne(id);
    }

}
