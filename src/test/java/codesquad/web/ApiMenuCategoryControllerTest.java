package codesquad.web;

import codesquad.domain.Account;
import codesquad.domain.AccountType;
import codesquad.domain.MenuCategory;
import codesquad.security.ManagerAccountHandlerMethodArgumentResolver;
import codesquad.service.MenuCategoryService;
import codesquad.web.dto.MenuCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiMenuCategoryControllerTest {
    private static Logger log = LoggerFactory.getLogger(ApiMenuCategoryControllerTest.class);

    public static final String URI_MENU_CATEGORY = "/api/menuCategory";

    private MockMvc mockMvc;

    @Mock
    private MenuCategoryService menuCategoryService;

    @Mock
    private ManagerAccountHandlerMethodArgumentResolver managerArgumentResolver;

    private MockManagerArgumentResolver mockManagerArgumentResolver = new MockManagerArgumentResolver();

    private JacksonTester<MenuCategoryDTO> jsonMenuCategoryDTO;

    private Account manager;

    @InjectMocks
    private ApiMenuCategoryController apiMenuCategoryController;

    private List<MenuCategory> categories = new ArrayList<>();

    @Before
    public void setup() {
        JacksonTester.initFields(this, ObjectMapper::new);

        mockMvc = MockMvcBuilders.standaloneSetup(apiMenuCategoryController)
                .setControllerAdvice(new ExceptionHandlerExceptionResolver())
                //.setCustomArgumentResolvers(managerArgumentResolver)
                .setCustomArgumentResolvers(mockManagerArgumentResolver)
                .build();

        MenuCategory fstCategory = new MenuCategory(null, "카테고리1");
        fstCategory.setId(1l);
        fstCategory.addChild(new MenuCategory(1l, "카테고리1의 하위 카테고리"));
        categories.add(fstCategory);
        categories.add(new MenuCategory(null, "카테고리2"));

        manager = new Account("manager@gmail.com", "!Password1234", "manager", "manager@gmail.com", AccountType.MANAGER);
    }

    @Test
    public void getCategoriesTest() throws Exception {
        //given
        when(menuCategoryService.findCategories())
                .thenReturn(categories);

        //when
        MockHttpServletResponse response = mockMvc.perform(get(URI_MENU_CATEGORY).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void createCategoryTest() throws Exception {
        //given
        when(menuCategoryService.create(any(MenuCategoryDTO.class)))
                .thenReturn(new MenuCategory());

        //when
        MockHttpServletResponse response = mockMvc.perform(
                post(URI_MENU_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMenuCategoryDTO.write(
                                new MenuCategoryDTO(0l, "카테고리2의 하위 카테고리", 3l))
                                .getJson()))
                .andReturn().getResponse();

        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        //given
        when(menuCategoryService.deleteById(1l))
                .thenReturn(new MenuCategory());
        when(managerArgumentResolver.supportsParameter((MethodParameter) notNull()))
                .thenReturn(true);
        when(managerArgumentResolver.resolveArgument(
                (MethodParameter) notNull()
                , (ModelAndViewContainer) notNull()
                , (NativeWebRequest) notNull()
                , (WebDataBinderFactory) notNull()
        )).thenReturn(manager);

        //when
        MockHttpServletResponse response = mockMvc.perform(
                delete(URI_MENU_CATEGORY + "/{id}", 1l))
                .andReturn().getResponse();

        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    static class MockManagerArgumentResolver extends ManagerAccountHandlerMethodArgumentResolver {

        @Override
        public Object resolveArgument(MethodParameter methodParameter,
                                      ModelAndViewContainer modelAndViewContainer,
                                      NativeWebRequest nativeWebRequest,
                                      WebDataBinderFactory webDataBinderFactory) throws Exception {
            return new Account("managerByMock@gmail.com", "!Password1234", "manager", "manager@gmail.com", AccountType.MANAGER);
        }
    }
}