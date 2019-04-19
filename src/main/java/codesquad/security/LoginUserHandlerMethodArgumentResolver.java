package codesquad.security;

import codesquad.domain.Account;
import codesquad.exception.account.UnAuthorizedException;
import codesquad.util.SessionUtils;
import org.hibernate.Session;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        Account account = SessionUtils.getUserFromSession(nativeWebRequest);
        if(!account.isGuestUser()){
            return account;
        }

        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        if(loginUser.isRequired()){
            throw new UnAuthorizedException("You're required Login!");
        }
        return account;
    }
}
