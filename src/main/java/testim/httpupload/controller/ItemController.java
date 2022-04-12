package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.domain.ItemType;
import testim.httpupload.domain.UploadFile;
import testim.httpupload.file.FileStore;
import testim.httpupload.service.CategoryService;
import testim.httpupload.service.ItemService;
import testim.httpupload.validation.form.ItemForm;
import testim.httpupload.validation.form.ItemUpdateForm;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    FileStore fileStore;

    @ModelAttribute("itemTypes")
    public List<ItemType> itemTypes() {
        List<ItemType> itemTypes = new ArrayList<>();
        itemTypes.add(new ItemType("HIGHEST", "최상"));
        itemTypes.add(new ItemType("BEST", "상"));
        itemTypes.add(new ItemType("LOWER", "중"));
        return itemTypes;
    }

    //
/*
    @GetMapping("/items")
    public String isHome(Pageable pageable, Model model) {
        Page<Item> itemList = itemService.findBoardList(pageable);
        itemList.stream().forEach(e -> e.getItemName());
        model.addAttribute("itemList", itemList);
        return "springform/springitems";

    }
*/

    @GetMapping("/items") //home
    public String isHome(Pageable pageable, Model model) {
       // List<Item> items = itemService.findAll();
        List<ItemCategory> categoryList = categoryService.findAll();
       // model.addAttribute("itemList", items);
        model.addAttribute("categoryList", categoryList);

        Page<Item> itemList = itemService.findItemList(pageable);
        itemList.stream().forEach(e -> e.getItemName());
        model.addAttribute("itemList", itemList);

        log.info("get! hie");
        return "springform/springitems";
    }


    @GetMapping("/items/table")
    public String ListTable(Model model) {
        List<Item> items = itemService.findAll();
        List<ItemCategory> categoryList = categoryService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("categoryList", categoryList);
        return "springform/tables";
    }



    @GetMapping("/items/new")
    public String newItem(@ModelAttribute Item item) {
        return "springform/springforms";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        List<UploadFile> uploadFiles = fileStore.storeFiles(form.getImageFiles());

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setImageFiles(uploadFiles);
        item.setItemType(form.getItemType());
        item.setCategoryType(form.getCategoryType());
        itemService.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        log.info("itemtype= ",item.getItemType());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "springform/springitem";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/items/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "springform/editForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) throws IOException {

        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setQuantity(form.getQuantity());
        itemParam.setPrice(form.getPrice());
        itemParam.setItemType(form.getItemType());
        itemParam.setImageFiles(storeImageFiles);
        itemParam.setCategoryType(form.getCategoryType());
        itemService.update(itemId, itemParam);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("items/{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId) {
        itemService.delete(itemId);

        return "redirect:/items";
    }


}
