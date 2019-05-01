package support.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

public class AcceptanceTest {

    @Autowired
    TestRestTemplate template;

    protected <T> ResponseEntity<T> sendPost(String uri, Object object, Class<T> responseType) {
        return template.exchange(uri, HttpMethod.POST, createHttpEntity(object), responseType);
    }

    protected <T> ResponseEntity<T> sendGet(String uri, Class<T> responseType) {
        return template.getForEntity(uri, responseType);
    }

    protected <T> ResponseEntity<T> sendDelete(String uri, Class<T> responseType) {
        return template.exchange(uri, HttpMethod.DELETE, createHttpEntity(null), responseType);
    }

    protected HttpEntity createHttpEntity(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }

}
