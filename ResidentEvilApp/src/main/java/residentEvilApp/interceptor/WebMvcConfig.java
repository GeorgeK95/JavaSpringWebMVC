package residentEvilApp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by George-Lenovo on 18/03/2018.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final UserAuthenticationInterceptor userInterceptor;

    @Autowired
    public WebMvcConfig(UserAuthenticationInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.userInterceptor);
    }
}
