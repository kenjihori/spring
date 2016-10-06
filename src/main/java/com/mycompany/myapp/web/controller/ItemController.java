package com.mycompany.myapp.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.domain.mapper.ItemMapper;
import com.mycompany.myapp.domain.model.Item;
import com.mycompany.myapp.service.ItemService;
import com.mycompany.myapp.web.form.ItemForm;
import com.mycompany.myapp.web.form.ValidationForm1;

@Controller
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    
    @Autowired
    ItemService itemService;
    
    @RequestMapping(value = "item", method = RequestMethod.GET)
    public String index(ItemForm form, Model model) {
        return "itemInput";
    }
    
    @RequestMapping(value = "item", method = RequestMethod.POST)
    public String create(@Validated ItemForm form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "itemInput";
        }
        // フォームからモデルを作成
        Item item = createItemFromForm(form);
        // 商品を登録
        itemService.createItem(item);
        // 完了画面に遷移
        model.addAttribute("itemForm", form);
        return "itemComplete";
    }
    
    @RequestMapping(value = "item/{itemCode}", method = RequestMethod.GET)
    public String show(HttpSession session, @PathVariable("itemCode") String itemCode, ItemForm form, Model model) {
        // 商品を取得
        Item item = itemService.findItem(itemCode);
        // 商品更新画面に遷移
        model.addAttribute("item", item);
        session.setAttribute("sessioitem", item);
        return "itemUpdate";
    }
    
    @RequestMapping(value = "item/{itemCode}", method = RequestMethod.POST)
    public String update(HttpSession session, @Validated ItemForm form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "itemUpdate";
        }
        // フォームからモデルを作成
        Item item = (Item)session.getAttribute("sessioitem");
        item = updateItemFromForm(item, form);
        // 商品を更新
        itemService.updateItem(item);
        // 完了画面に遷移
        model.addAttribute("itemForm", form);
        return "itemComplete";
    }
    
    private Item createItemFromForm(ItemForm form) {
        Item item = new Item();
        item.setId(form.getItemCode());;
        item.setName(form.getItemName());
        return item;
    }
    
    private Item updateItemFromForm(Item item, ItemForm form) {
        item.setName(form.getItemName());
        return item;
    }

}
