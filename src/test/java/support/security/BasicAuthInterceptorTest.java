package support.security;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.intercepter.BasicAuthInterceptor;
import codesquad.util.SessionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Base64;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasicAuthInterceptorTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void preHandleTest() throws Exception {
        //given
        String userId = "userId";
        String password = "password";
        MockHttpServletRequest request = basicAuthHttpRequest(userId, password);
        Account loginAccount = new Account(userId, password, "name", "javajigi@slipp.net");
        when(accountRepository.findByUserId(userId)).thenReturn(Optional.of(loginAccount));

        //when
        basicAuthInterceptor.preHandle(request, null, null);

        //then
        assertThat(request.getSession().getAttribute(SessionUtils.USER_SESSION_KEY), is(loginAccount));

    }

    private MockHttpServletRequest basicAuthHttpRequest(String userId, String password) {
        String encodedBasicAuth = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", userId, password).getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
        return request;
    }
}
