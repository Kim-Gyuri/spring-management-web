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

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        Item product = itemService.findById(id);

        CartForm cartForm = new CartForm();

        cartForm.setItem(product);
        
        log.info("item={}", product.getItemName());
        log.info("productInfo={}", cartForm.getItem().getItemName());
        log.info("items={}확인해보자.",product.getQuantity());

        model.addAttribute("product", cartForm);


        return "product/productInfo";
    }

    @PostMapping("/products/{id}")
    public String getCart(@ModelAttribute("product") CartForm form, BindingResult errors) {
        Item product = form.getItem();
        int count = form.getCount();
        Long orderId = orderService.order(product, count);

        log.info("orderQuantity={}", form.getCount());
        log.info("남아있는 재고량={}", product.getQuantity());

         //  Order order = orderService.findById(orderId);
        //   List<OrderItem> orderItems = order.getOrderItems();
       //    log.info("stockQuantity={}",orderItems.get(0).getItem().getQuantity());
      //     log.info("itemId={}", orderItems.get(0).getItem().getId());

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
            log.info("order1.getOrderItems().get(0).getItem().getItemName() = {}", cart.getOrderItems().get(0).getItem().getItemName());
            log.info("order1.getOrderItems().get(0).getItem().count = {}", cart.getOrderItems().get(0).getCount());
            log.info("order1.getOrderItems().size() = {}", cart.getOrderItems().size());
            log.info("cart = {} ", cart.getOrderItems().size());
        }
        return "cart/cart";
    }




}
