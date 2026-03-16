package fit.se.cms_layredarchitechture;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mở khóa cho toàn bộ API
                .allowedOrigins("http://localhost:5173") // Cấp "visa" cho React Vite
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Cho phép các thao tác này
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}