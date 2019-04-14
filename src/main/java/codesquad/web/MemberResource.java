package codesquad.web;

import codesquad.exception.UnAuthenticationException;
import codesquad.service.AccountService;
import codesquad.util.SessionUtils;
import codesquad.web.dto.AccountLoginDTO;
import codesquad.web.dto.AccountRegistrationDTO;
import com.sun.jndi.toolkit.url.Uri;
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
public class MemberResource {

    private static final Logger log = LoggerFactory.getLogger(MemberResource.class);

    @Autowired
    AccountService accountService;

    @PostMapping("")
    public ResponseEntity<Void> createMember(@Valid @RequestBody AccountRegistrationDTO account) {
        accountService.save(account);
        return makeDefaultResponseEntity("/login.html", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpSession session
            , @Valid @RequestBody AccountLoginDTO accountLoginDTO){
        //TODO : 이 valid 에서 걸러져서 badrequest나감,  즉 request에서 RequestBody 매핑이 안되었음.
        log.debug("login 포스트가 전달 되어 함수가 실행됨.");
        try {
            session.setAttribute(SessionUtils.USER_SESSION_KEY, accountService.findAccount(accountLoginDTO));
        } catch (UnAuthenticationException e) {
            return makeDefaultResponseEntity("member/login", HttpStatus.NOT_FOUND);
        }
        return  makeDefaultResponseEntity("/", HttpStatus.OK);
    }

    public ResponseEntity<Void> makeDefaultResponseEntity(String uri, HttpStatus httpStatus){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));
        return new ResponseEntity<>(headers, httpStatus);
    }

}
