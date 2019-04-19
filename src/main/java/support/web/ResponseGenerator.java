package support.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseGenerator {

    public static ResponseEntity<Void> makeDefaultResponseEntity(String uri, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(uri));
        return new ResponseEntity<>(headers, httpStatus);
    }

    public static <T> ResponseEntity<T> makeCreatedResponseEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<T>(body, headers, HttpStatus.CREATED);
    }

}
