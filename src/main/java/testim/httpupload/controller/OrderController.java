package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("product", cartForm);

        return "product/productInfo";
    }

    @PostMapping("/products/{id}")
    public String getCart(@PathVariable Long id, @ModelAttribute("product") CartForm form) {

        Long order = orderService.order(form.getItem(), form.getCount());

        Order getOrder = orderService.findById(order);
        log.info("getOrder={}", getOrder.getOrderItems().size());

        return "redirect:/carts";
    }


    @GetMapping("/carts")
    public String orderList(Model model) {
        List<Order> carts = orderService.findAll();
        for (Order cart : carts) {
            System.out.println("order1.getOrderItems().get(0).getItem().getItemName() = " + cart.getOrderItems().get(0).getItem().getItemName());
            System.out.println("order1.getOrderItems().get(0).getItem().count = " + cart.getOrderItems().get(0).getCount());
            System.out.println("order1.getOrderItems().size() = " + cart.getOrderItems().size());
            System.out.println("cart = " + cart.getOrderItems().size());
        }
        model.addAttribute("carts", carts);
        return "cart/cart";
    }




}
