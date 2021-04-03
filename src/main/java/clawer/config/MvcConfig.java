package clawer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
        registry.addMapping("/**")
        .allowedHeaders("*")
        .allowedMethods("*")
        .maxAge(1800)
        //.allowCredentials(true)
        .allowedOrigins("http://localhost:8082");
	}
 
	
}
