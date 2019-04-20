package codesquad.intercepter;

import codesquad.domain.Account;
import codesquad.exception.account.UnAuthenticationException;
import codesquad.exception.account.UnAuthorizedException;
import codesquad.util.SessionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.invoke.MethodHandle;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object loginAccount = SessionUtils.getLoginUser(request.getSession());
        if(loginAccount == null){
            throw new UnAuthenticationException();
        }

        Account account = (Account)loginAccount;
        if(!account.isManager()){
            throw new UnAuthorizedException("you're not manager");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }

}
