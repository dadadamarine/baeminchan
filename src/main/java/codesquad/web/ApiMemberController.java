package codesquad.web;

import codesquad.service.AccountService;
import codesquad.util.SessionUtils;
import codesquad.web.dto.AccountLoginDTO;
import codesquad.web.dto.AccountRegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static support.web.ResponseGenerator.makeDefaultResponseEntity;

@RestController
@RequestMapping("/member")
public class ApiMemberController {

    private static final Logger log = LoggerFactory.getLogger(ApiMemberController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<Void> createMember(@Valid @RequestBody AccountRegistrationDTO accountRegistrationDTO) {
        accountService.save(accountRegistrationDTO);

        return makeDefaultResponseEntity("/login", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpSession session, @Valid @RequestBody AccountLoginDTO accountLoginDTO) {
        session.setAttribute(SessionUtils.USER_SESSION_KEY, accountService.findAccount(accountLoginDTO));
        return makeDefaultResponseEntity("/", HttpStatus.OK);
    }

}
