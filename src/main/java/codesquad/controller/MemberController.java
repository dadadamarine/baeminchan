package codesquad.controller;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/join")
    public String form(){
        return "join";
    }

    @PostMapping("")
    public String join(){
        log.debug("회원가입 요청 왔다.");
        return "index";
    }


}
