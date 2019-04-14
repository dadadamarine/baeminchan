package codesquad.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login.html")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/member/join")
    public String form() {
        return "join";
    }

}
