package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import testim.httpupload.domain.Address;
import testim.httpupload.domain.User;
import testim.httpupload.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(UserForm form) {

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        User user = new User();
        user.setName(form.getName());
        user.setAddress(address);

        userService.join(user);
        return "redirect:/items";
    }

}
