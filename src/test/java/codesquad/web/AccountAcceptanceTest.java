package codesquad.web;

import codesquad.domain.MemberType;
import codesquad.web.dto.AccountLoginDTO;
import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.web.dto.AccountRegistrationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountAcceptanceTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void create() throws Exception {
        AccountRegistrationDTO account = new AccountRegistrationDTO.Builder("test1@google.com", "!Password1234", "!Password1234", "name").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(account, headers);
        ResponseEntity<String> response = template.postForEntity("/member", entity, String.class );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(true);
    }

    @Test
    public void createWithInvalidPassword() throws Exception {
        AccountRegistrationDTO account = new AccountRegistrationDTO.Builder("test2@google.com", "!Password", "!Password1234", "name").build();
        ResponseEntity<String> response = template.postForEntity("/member",account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(false);
    }

    @Test
    public void createWithInvalidUserId() throws Exception {
        Account account = new Account(3L, "t", "password", "testname", "test@gmail.com", MemberType.MEMBER);
        ResponseEntity<String> response = template.postForEntity("/member",account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(false);
    }

    @Test
    public void login() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("test@google.com", "!Password1234");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(account, headers);
        ResponseEntity<Void> response = template.exchange("/member/login", HttpMethod.POST, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/");
    }

    @Test
    public void loginWithNotFoundAccount() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("testes@google.com", "!Password1234");
        ResponseEntity<String> response = template.postForEntity("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/login");
    }

    @Test
    public void loginWithUnmatchPassword() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("test@google.com", "loginpassword");
        ResponseEntity<String> response = template.postForEntity("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/login");
    }
}
