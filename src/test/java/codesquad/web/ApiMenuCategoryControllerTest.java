package codesquad.web;

import codesquad.domain.Account;
import codesquad.domain.MenuCategory;
import codesquad.security.ManagerAccountHandlerMethodArgumentResolver;
import codesquad.service.MenuCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiMenuCategoryControllerTest {
    private static Logger log = LoggerFactory.getLogger(ApiMenuCategoryControllerTest.class);

    public static final String URI_MENU_CATEGORY = "/api/menuCategory";

    private MockMvc mockMvc;

    @Mock
    private MenuCategoryService menuCategoryService;

    @Mock
    private ManagerAccountHandlerMethodArgumentResolver authArgumentResolver;

    @InjectMocks
    private ApiMenuCategoryController apiMenuCategoryController;

    private List<MenuCategory> categories = new ArrayList<>();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiMenuCategoryController)
                .setControllerAdvice(new ExceptionHandlerExceptionResolver())
                .setCustomArgumentResolvers(new ManagerAccountHandlerMethodArgumentResolver())
                .build();

        MenuCategory fstCategory = new MenuCategory(null, "카테고리1");
        fstCategory.setId(1l);
        fstCategory.addChild(new MenuCategory(1l, "카테고리1의 하위 카테고리"));
        categories.add(fstCategory);
        categories.add(new MenuCategory(null, "카테고리2"));
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
        when(menuCategoryService.findCategories()).thenReturn(categories);
        when(authArgumentResolver.resolveArgument(
                any(MethodParameter.class)
                , any(ModelAndViewContainer.class)
                , any(NativeWebRequest.class)
                , any(WebDataBinderFactory.class)
        )).thenReturn(new Account());

        //when
        MockHttpServletResponse response = mockMvc.perform(get(URI_MENU_CATEGORY).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //then
        log.debug(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}