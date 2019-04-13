package codesquad.web;

import codesquad.domain.Account;
import codesquad.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    AccountService accountService;

    @PostMapping("")
    public ResponseEntity<Void> createMember(@Valid @RequestBody Account account) {
        accountService.save(account);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login.html"));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //TODO : controllerAdvice동반한 컨트롤러 테스트 구현


}
