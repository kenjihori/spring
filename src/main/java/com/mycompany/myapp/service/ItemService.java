package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.mapper.ItemMapper;
import com.mycompany.myapp.domain.model.Item;

@Transactional
@Service
public class ItemService {

    @Autowired
    ItemMapper itemMapper;

    public Item findItem(String id) {
        return itemMapper.findOne(id);
    }

}
