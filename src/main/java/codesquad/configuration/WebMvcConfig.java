package codesquad.configuration;

import codesquad.intercepter.AdminInterceptor;

import codesquad.intercepter.BasicAuthInterceptor;
import codesquad.security.AdminAccountHandlerMethodArgumentResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AdminAccountHandlerMethodArgumentResolver managerAccountHandlerMethodArgumentResolver() {
        return new AdminAccountHandlerMethodArgumentResolver();
    }

    @Configuration
    @Profile("test")
    public class TestWebMvcConfig extends WebMvcConfig{

        @Bean
        public BasicAuthInterceptor basicAuthInterceptor(){
            return new BasicAuthInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(basicAuthInterceptor());
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(managerAccountHandlerMethodArgumentResolver());
    }

    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor())
                .addPathPatterns("/admin");
    }
}
