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
        // 商品コードが入力されていない場合はチェックしない（フォームクラスのNotNullでチェックする）
        if(value == null) {
            return true;
        }
        // 商品マスタより商品を取得する
        Item item = itemService.findItem(value);
        // 商品が存在する場合はチェックOK、存在しない場合はチェックNGとする
        if(item != null) {
            return true;
        } else {
            return false;
        }
    }
}
