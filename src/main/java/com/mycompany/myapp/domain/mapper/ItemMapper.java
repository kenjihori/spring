package com.mycompany.myapp.domain.mapper;

import com.mycompany.myapp.domain.model.Item;

public interface ItemMapper {

    Item findOne(String id);

}
