package codesquad.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BasicAuthenticationIntercepter extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(BasicAuthenticationIntercepter.class);

}
