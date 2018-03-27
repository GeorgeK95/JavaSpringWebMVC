package bg.galaxi.nuggets.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by George-Lenovo on 26/03/2018.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final PreferencesExtractInterceptor preferencesExtractInterceptor;

    @Autowired
    public WebMvcConfig(PreferencesExtractInterceptor preferencesExtractInterceptor) {
        this.preferencesExtractInterceptor = preferencesExtractInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.preferencesExtractInterceptor);
    }
}
