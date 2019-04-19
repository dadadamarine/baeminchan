package codesquad.web;

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
    public void index_test() {
        ResponseEntity<String> reponse = sendGet("/", String.class);

        log.info("body : {}", reponse);
        assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void api_get_test(){
        ResponseEntity<MenuCategory> response = sendGet("/api/menuCategory", MenuCategory.class);

        log.info("body : {}", response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getChildren().size()).isEqualTo(7);
        assertThat(response.getBody().getName()).isEqualTo("categories");
    }

    @Test
    public void api_create_test(){
        MenuCategoryDTO category = new MenuCategoryDTO();
        category.setName("새로운 자식");
        category.setParentId(1l);
        ResponseEntity<MenuCategory> response = sendPost("/api/menuCategory", category, MenuCategory.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("새로운 자식");
    }

    @Test
    public void api_create_category_test(){
        MenuCategoryDTO category = new MenuCategoryDTO();
        category.setName("새로운 카테고리");
        ResponseEntity<MenuCategory> response = sendPost("/api/menuCategory", category, MenuCategory.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("새로운 카테고리");
    }

    @Test
    public void api_delete_test(){
        ResponseEntity<MenuCategory> response = sendDelete("/api/menuCategory/1", MenuCategory.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("밑반찬");
    }

}
