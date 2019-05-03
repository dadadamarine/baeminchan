package support.test;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.exception.account.UnAuthenticationException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {
    private static final String DEFAULT_MANAGER_ACCOUNT_EMAIL = "admin@admin.com";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    public TestRestTemplate template() {
        return testRestTemplate;
    }

    public TestRestTemplate basicAuthTemplate() {
        return basicAuthTemplate(getDefaultManager());
    }

    private Account getDefaultManager() {
        return accountRepository.findByUserId(DEFAULT_MANAGER_ACCOUNT_EMAIL).orElseThrow(UnAuthenticationException::new);
    }

    public TestRestTemplate basicAuthTemplate(Account account) {
        return testRestTemplate.withBasicAuth(account.getUserId(), account.getPassword());
    }

    protected <T> ResponseEntity<T> sendPost(String uri, Object object, Class<T> responseType) {
        return template().exchange(uri, HttpMethod.POST, createHttpEntity(object), responseType);
    }

    protected <T> ResponseEntity<T> sendGet(String uri, Class<T> responseType) {
        return template().getForEntity(uri, responseType);
    }

    protected <T> ResponseEntity<T> sendDelete(String uri, Class<T> responseType) {
        return template().exchange(uri, HttpMethod.DELETE, createHttpEntity(null), responseType);
    }

    protected <T> ResponseEntity<T> sendPostWithDefaultManager(String uri, Object object, Class<T> responseType) {
        return basicAuthTemplate().exchange(uri, HttpMethod.POST, createHttpEntity(object), responseType);
    }

    protected <T> ResponseEntity<T> sendGetWithDefaultManager(String uri, Class<T> responseType) {
        return basicAuthTemplate().getForEntity(uri, responseType);
    }

    protected <T> ResponseEntity<T> sendDeleteWithDefaultManager(String uri, Class<T> responseType) {
        return basicAuthTemplate().exchange(uri, HttpMethod.DELETE, createHttpEntity(null), responseType);
    }

    protected HttpEntity createHttpEntity(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }

}
