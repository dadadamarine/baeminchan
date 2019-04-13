package codesquad.web;

import codesquad.web.dto.AccountDTO;
import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountAcceptanceTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AccountRepository accountRepository;
    @Before
    public void test(){
        accountRepository.findAll();
    }

    @Test
    public void create() throws Exception {
        Account account = new Account(2L, "test@google.com", "!Password1234", "testname", "test@gmail.com",1L);
        ResponseEntity<String> response = template.postForEntity("/member",account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountRepository.findById(account.getId())).isNotEmpty();
    }

    @Test
    public void createWithInvalidPassword() throws Exception {
        Account account = new Account(3L, "test@google.com", "password", "testname", "test@gmail.com",1L);
        ResponseEntity<String> response = template.postForEntity("/member",account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findById(account.getId())).isEmpty();
    }

    @Test
    public void createWithInvalidId() throws Exception {
        Account account = new Account(3L, "t", "password", "testname", "test@gmail.com",1L);
        ResponseEntity<String> response = template.postForEntity("/member",account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(accountRepository.findById(account.getId())).isEmpty();
    }

    @Test
    public void login() throws Exception {
        AccountDTO account = new AccountDTO("test@google.com", "!Password1234");
        ResponseEntity<String> response = template.postForEntity("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/");
    }

    @Test
    public void loginWithNotFoundAccount() throws Exception {
        AccountDTO account = new AccountDTO("testes@google.com", "!Password1234");
        ResponseEntity<String> response = template.postForEntity("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/login");
    }

    @Test
    public void loginWithUnmatchPassword() throws Exception {
        AccountDTO account = new AccountDTO("test@google.com", "loginpassword");
        ResponseEntity<String> response = template.postForEntity("/member/login", account, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/login");
    }
}
