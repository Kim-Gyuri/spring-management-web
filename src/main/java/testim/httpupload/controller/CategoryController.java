package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import testim.httpupload.domain.ItemCategory;
import testim.httpupload.service.CategoryService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{categoryId}")
    public String showOne(@PathVariable Long categoryId, Model model) {
        ItemCategory category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        log.info("categoryInit={}", category.getCategoryId());
        return "category/categoryPage";
        //return "ok";
    }
}
