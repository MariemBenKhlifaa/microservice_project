package tn.esprit.reclamationservice.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map the URL path "/uploads/**" to the local directory "file:/path/to/your/uploads/directory/"
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:./reclamation-service/uploads/");
    }
}
