package testim.httpupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{type}")
    public String showOne(@PathVariable Long id, Model model) {
        ItemCategory category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/categoryPage";
    }
}
