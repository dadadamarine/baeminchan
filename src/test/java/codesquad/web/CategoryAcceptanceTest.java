package codesquad.web;

import codesquad.domain.MenuCategoryRepository;
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
public class CategoryAcceptanceTest extends AcceptanceTest {
    private static Logger log = LoggerFactory.getLogger(CategoryAcceptanceTest.class);

    @Autowired
    MenuCategoryRepository menuCategoryRepository;

    @Test
    public void get_test() {
        ResponseEntity<String> reponse = sendGet("/", String.class);

        log.info("body : {}", reponse);
        assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void create_test(){

    }

    @Test
    public void delete_test(){

    }

}
