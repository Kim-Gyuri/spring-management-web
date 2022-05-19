package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import testim.httpupload.domain.User;
import testim.httpupload.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user) {

 //       Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        userRepository.save(user);
        return "redirect:/items";
    }

}
