package carDealer.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final LoggerInterceptor loggerInterceptor;
    private final UserAuthenticationInterceptor userInterceptor;

    @Autowired
    public WebMvcConfig(LoggerInterceptor loggerInterceptor, UserAuthenticationInterceptor userInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loggerInterceptor);
        registry.addInterceptor(this.userInterceptor);
    }
}
