package com.mycompany.myapp.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.domain.model.Item;
import com.mycompany.myapp.service.ItemService;

public class ItemCodeExistsValidator implements ConstraintValidator<ItemCodeExists, String> {

    @Autowired
    ItemService itemService;
    
    @Override
    public void initialize(ItemCodeExists annotation) {
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // ���i�}�X�^��菤�i���擾
        Item item = itemService.findItem(Integer.valueOf(value));
        // ���i�����݂���ꍇ�̓`�F�b�NOK�A���݂��Ȃ��ꍇ�̓`�F�b�NNG�Ƃ���
        if(item != null) {
            return true;
        } else {
            return false;
        }
    }
}
