package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.ItemType;
import testim.httpupload.domain.UploadFile;
import testim.httpupload.file.FileStore;
import testim.httpupload.repository.ItemRepository;
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

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @ModelAttribute("itemTypes")
    public List<ItemType> itemTypes() {
        List<ItemType> itemTypes = new ArrayList<>();
        itemTypes.add(new ItemType("HIGHEST", "최상"));
        itemTypes.add(new ItemType("BEST", "상"));
        itemTypes.add(new ItemType("LOWER", "중"));
        return itemTypes;
    }

    @GetMapping("/items") //home
    public String isHome(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "springform/springitems";
    }

    @GetMapping("/items/table") //home
    public String ListTable(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
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
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);
        log.info("itemtype= ",item.getItemType());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id);
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
        Item item = itemRepository.findById(itemId);
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
        itemRepository.update(itemId, itemParam);

        return "redirect:/items/{itemId}";
    }


}
