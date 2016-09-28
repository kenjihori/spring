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
        // ���i�R�[�h�����͂���Ă��Ȃ��ꍇ�̓`�F�b�N���Ȃ��i�t�H�[���N���X��NotNull�Ń`�F�b�N����j
        if(value == null) {
            return true;
        }
        // ���i�}�X�^��菤�i���擾����
        Item item = itemService.findItem(value);
        // ���i�����݂���ꍇ�̓`�F�b�NOK�A���݂��Ȃ��ꍇ�̓`�F�b�NNG�Ƃ���
        if(item != null) {
            return true;
        } else {
            return false;
        }
    }
}
