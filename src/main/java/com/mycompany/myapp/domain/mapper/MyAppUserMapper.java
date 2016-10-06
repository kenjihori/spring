package com.mycompany.myapp.domain.mapper;

import com.mycompany.myapp.domain.model.MyAppUser;

public interface MyAppUserMapper {

    MyAppUser findOne(String id);

}
