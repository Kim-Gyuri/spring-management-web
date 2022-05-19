package testim.httpupload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import testim.httpupload.domain.SessionConst;
import testim.httpupload.domain.User;
import testim.httpupload.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        User loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) { //로그인시도중 실패처리 -> reject()로 글로벌 처리
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 다릅니다.");
            return "login/loginForm";
        }
        //로그인 성공 처리 todo

        //세션, 세션이 있으면 있는 세션을 반환, 없다면 신규세션을 생성한다.
        HttpSession session = request.getSession(); //default:true이다.
        //세션에 로그인 회원 정보 생성
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        return "redirect:" + redirectURL;
    }
}
