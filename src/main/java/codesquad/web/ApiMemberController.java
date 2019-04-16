package codesquad.web;

import codesquad.service.AccountService;
import codesquad.util.SessionUtils;
import codesquad.web.dto.AccountLogin;
import codesquad.web.dto.AccountRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/member")
public class ApiMemberController {

    private static final Logger log = LoggerFactory.getLogger(ApiMemberController.class);

    @Autowired
    AccountService accountService;

    @PostMapping("")
    public ResponseEntity<Void> createMember(@Valid @RequestBody AccountRegistration accountRegistration) {
        accountService.save(accountRegistration);
        return makeDefaultResponseEntity("/login.html", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpSession session, @Valid @RequestBody AccountLogin accountLogin) {
        session.setAttribute(SessionUtils.USER_SESSION_KEY, accountService.findAccount(accountLogin));
        return makeDefaultResponseEntity("/", HttpStatus.OK);
    }

    public ResponseEntity<Void> makeDefaultResponseEntity(String uri, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));
        return new ResponseEntity<>(headers, httpStatus);
    }

}
