package codesquad.intercepter;

import codesquad.domain.Account;
import codesquad.domain.AccountRepository;
import codesquad.exception.account.UnAuthenticationException;
import codesquad.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(BasicAuthInterceptor.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String base64Credentials;
        try {
            base64Credentials = getEncodedCredentials(request);
            String[] credentialValues = getDecodedCredentials(base64Credentials);
            String userId = credentialValues[0];
            String password = credentialValues[1];
            log.debug("userId : {}", userId);
            log.debug("password : {}", password);
            login(request, userId, password);
            return true;
        } catch (UnAuthenticationException e) {
            return true;
        }
    }

    public String getEncodedCredentials(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Basic")) {
            throw new UnAuthenticationException();
        }
        return authorization.substring("Basic".length()).trim();
    }

    public String[] getDecodedCredentials(String base64Credentials) {
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        return credentials.split(":", 2);
    }

    public void login(HttpServletRequest request, String userId, String password) {
        Account account = accountRepository.findByUserId(userId).orElseThrow(UnAuthenticationException::new);

        if (account.matchPassword(password)) {
            request.getSession().setAttribute(SessionUtils.USER_SESSION_KEY, account);
        }
    }
}
