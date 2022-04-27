package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import testim.httpupload.domain.Item;
import testim.httpupload.domain.Order;
import testim.httpupload.file.FileStore;
import testim.httpupload.service.CartService;
import testim.httpupload.service.CategoryService;
import testim.httpupload.service.ItemService;
import testim.httpupload.service.OrderService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;

    @Autowired
    FileStore fileStore;


    @GetMapping("/products/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item product = itemService.findById(id);
        log.info("item={}", product.getItemName());
        CartForm cartForm = new CartForm();
        cartForm.setItem(product);
        log.info("productInfo={}", cartForm.getItem().getItemName());
        model.addAttribute("product", cartForm);

        return "product/productInfo";
    }

    @PostMapping("/products/{id}")
    public String getCart(@ModelAttribute("product") CartForm form, BindingResult errors) {
        Item product = form.getItem();
        int count = form.getCount();
        orderService.order(product, count);
        log.info("get order Post!");
        if (errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
        }


        return "redirect:/cartList";
    }


    @GetMapping("/cartList")
    public String orderList(Model model) {
        List<Order> product = orderService.findAll();
        model.addAttribute("product", product);
        for (Order cart : product) {
            System.out.println("order1.getOrderItems().get(0).getItem().getItemName() = " + cart.getOrderItems().get(0).getItem().getItemName());
            System.out.println("order1.getOrderItems().get(0).getItem().count = " + cart.getOrderItems().get(0).getCount());
            System.out.println("order1.getOrderItems().size() = " + cart.getOrderItems().size());
            System.out.println("cart = " + cart.getOrderItems().size());
        }
        return "cart/cart";
    }




}
