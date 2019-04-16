package codesquad.web;

import codesquad.domain.AccountRepository;
import codesquad.domain.MemberType;
import codesquad.web.dto.AccountLogin;
import codesquad.web.dto.AccountRegistration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountAcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(AccountAcceptanceTest.class);

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void create() throws Exception {
        AccountRegistration account = new AccountRegistration
                .Builder("test1@google.com", "!Password1234", "!Password1234", "name")
                .phoneNumber("010-1234-1234")
                .email("test@google.com")
                .type(MemberType.MEMBER)
                .build();

        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(true);
    }

    @Test
    public void createWithInvalidPassword() throws Exception {
        AccountRegistration account = new AccountRegistration.Builder("test2@google.com", "!Password", "!Password1234", "name").build();
        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(false);
    }

    @Test
    public void createWithInvalidUserId() throws Exception {
        AccountRegistration account = new AccountRegistration.Builder("t", "!Password1234", "!Password1234", "test@gmail.com").build();
        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(false);
    }

    @Test
    public void login() throws Exception {
        AccountLogin account = new AccountLogin("test@google.com", "!Test1234");
        ResponseEntity<Void> response = sendPost("/member/login", account, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/");
    }

    @Test
    public void loginWithNotFoundAccount() throws Exception {
        AccountLogin account = new AccountLogin("testes@google.com", "!Test1234");
        ResponseEntity<String> response = sendPost("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("member/login");
    }

    @Test
    public void loginWithUnmatchPassword() throws Exception {
        AccountLogin account = new AccountLogin("test@google.com", "!Test12345");
        ResponseEntity<String> response = sendPost("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("member/login");
    }

    @Test
    public void loginWithInvalidPassword() throws Exception {
        AccountLogin account = new AccountLogin("test@google.com", "!test12345");
        ResponseEntity<String> response = sendPost("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("violation error message : {}", response.getBody());

    }

    public <T> ResponseEntity<T> sendPost(String uri, Object object, Class<T> responseType) {
        return template.exchange(uri, HttpMethod.POST, createHttpEntity(object), responseType);
    }

    public HttpEntity createHttpEntity(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }

}
