package codesquad.acceptance;

import codesquad.domain.AccountRepository;
import codesquad.domain.AccountType;
import codesquad.web.dto.AccountLoginDTO;
import codesquad.web.dto.AccountRegistrationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AccountAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(AccountAcceptanceTest.class);


    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void create() throws Exception {
        AccountRegistrationDTO account = AccountRegistrationDTO
                .builder("test1@google.com", "!Password1234", "!Password1234", "name")
                .phoneNumber("010-1234-1234")
                .email("test@google.com")
                .type(AccountType.MEMBER)
                .build();

        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(true);
    }

    @Test
    public void create_duplicate_userId() throws Exception {
        AccountRegistrationDTO account = AccountRegistrationDTO
                .builder("test@google.com", "!Password1234", "!Password1234", "name")
                .phoneNumber("010-1234-1234")
                .email("test@google.com")
                .type(AccountType.MEMBER)
                .build();

        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createWithInvalidPassword() throws Exception {
        AccountRegistrationDTO account = AccountRegistrationDTO.builder("test2@google.com", "!Password", "!Password1234", "name").build();
        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(false);
    }

    @Test
    public void createWithInvalidUserId() throws Exception {
        AccountRegistrationDTO account = AccountRegistrationDTO.builder("t", "!Password1234", "!Password1234", "test@gmail.com").build();
        ResponseEntity<String> response = sendPost("/member", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findByUserId(account.getUserId()).isPresent()).isEqualTo(false);
    }

    @Test
    public void login() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("test@google.com", "!Test1234");
        ResponseEntity<Void> response = sendPost("/member/login", account, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/");
    }

    @Test
    public void loginWithNotFoundAccount() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("testes@google.com", "!Test1234");
        ResponseEntity<String> response = sendPost("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("member/login");
    }

    @Test
    public void loginWithUnmatchPassword() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("test@google.com", "!Test12345");
        ResponseEntity<String> response = sendPost("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("member/login");
    }

    @Test
    public void loginWithInvalidPassword() throws Exception {
        AccountLoginDTO account = new AccountLoginDTO("test@google.com", "!test12345");
        ResponseEntity<String> response = sendPost("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        log.debug("violation error message : {}", response.getBody());

    }
}
