package codesquad.acceptance;

import codesquad.domain.MenuCategory;
import codesquad.domain.MenuCategoryRepository;
import codesquad.web.dto.MenuCategoryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import support.test.AcceptanceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CategoryAcceptanceTest extends AcceptanceTest {
    //TODO : BasicAuthorizationInterceptor를 통한 AcceptanceTest구현
    private static Logger log = LoggerFactory.getLogger(CategoryAcceptanceTest.class);

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Test
    public void index_test() {
        ResponseEntity<String> reponse = sendGet("/", String.class);
        log.info("body : {}", reponse);
        assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void api_get_test() {
        ResponseEntity<List> response = sendGet("/api/menuCategory", List.class);

        log.info("body : {}", response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void api_create_test() {
        MenuCategoryDTO category = new MenuCategoryDTO();
        category.setName("새로운 자식");
        category.setParentId(1l);
        ResponseEntity<MenuCategory> response = sendPost("/api/menuCategory", category, MenuCategory.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo("새로운 자식");
    }

    @Test
    public void api_create_category_test() {
        MenuCategoryDTO category = new MenuCategoryDTO();
        category.setName("새로운 카테고리");
        ResponseEntity<MenuCategory> response = sendPost("/api/menuCategory", category, MenuCategory.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo("새로운 카테고리");
    }

    @Test
    public void api_delete_test() {
        MenuCategoryDTO category = new MenuCategoryDTO();
        category.setName("새로운 삭제된 카테고리");
        ResponseEntity<MenuCategory> responseByPost = sendPost("/api/menuCategory", category, MenuCategory.class);

        ResponseEntity<MenuCategory> responseByDelete = sendDelete("/api/menuCategory/" + responseByPost.getBody().getId(), MenuCategory.class);

        assertThat(responseByDelete.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseByDelete.getBody().getName()).isEqualTo("새로운 삭제된 카테고리");
    }

}
