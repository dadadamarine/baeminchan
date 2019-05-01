package codesquad.intercepter;

import codesquad.domain.Account;
import codesquad.exception.account.UnAuthenticationException;
import codesquad.exception.account.UnAuthorizedException;
import codesquad.util.SessionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object loginAccount = SessionUtils.getLoginUser(request.getSession());
        if (loginAccount == null) {
            throw new UnAuthenticationException();
        }

        Account account = (Account) loginAccount;
        if (!account.getType().isAdmin()) {
            throw new UnAuthorizedException("you're not manager");
        }
        return true;
    }
}
