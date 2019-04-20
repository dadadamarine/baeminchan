package codesquad.web;

import codesquad.domain.Account;
import codesquad.service.MenuCategoryService;
import codesquad.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("categories", menuCategoryService.findCategories());
        if (SessionUtils.isLogin(session)) {
            model.addAttribute("account", SessionUtils.getLoginUser(session));
        }
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Account loginAccount, Model model) {
        model.addAttribute("categories", menuCategoryService.findCategories());
        return "admin";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/member/join")
    public String form() {
        return "join";
    }

}
