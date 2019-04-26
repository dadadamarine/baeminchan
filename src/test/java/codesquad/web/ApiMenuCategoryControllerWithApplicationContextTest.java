package codesquad.web;

import codesquad.domain.MenuCategory;
import codesquad.service.MenuCategoryService;
import codesquad.web.dto.MenuCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ApiMenuCategoryController.class, secure = false)
public class ApiMenuCategoryControllerWithApplicationContextTest {
    private static Logger log = LoggerFactory.getLogger(ApiMenuCategoryControllerWithApplicationContextTest.class);

    public static final String URI_MENU_CATEGORY = "/api/menuCategory";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuCategoryService menuCategoryService;

    private JacksonTester<MenuCategoryDTO> jsonMenuCategoryDTO;
    private JacksonTester<MenuCategory> jsonMenuCategory;

    private List<MenuCategory> categories = new ArrayList<>();

    @Before
    public void setup() {
        JacksonTester.initFields(this, ObjectMapper::new);

        MenuCategory fstCategory = new MenuCategory(1l, "카테고리1");
        MenuCategory subCategory = new MenuCategory(2l, "카테고리1의 하위 카테고리");
        fstCategory.addChild(subCategory);
        subCategory.setParent(fstCategory);
        categories.add(fstCategory);
        categories.add(new MenuCategory(3l, "카테고리2"));
    }

    @Test
    public void create_test_not_manager_fail() throws Exception {
        //when
        MockHttpServletResponse response = mockMvc.perform(
                post(URI_MENU_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMenuCategoryDTO.write(
                                new MenuCategoryDTO(0l, "카테고리2의 하위 카테고리", 3l))
                                .getJson()))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void delete_test_not_manager_fail() throws Exception {
        //when
        MockHttpServletResponse response = mockMvc.perform(
                delete(URI_MENU_CATEGORY + "/{id}", 1l))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

}
