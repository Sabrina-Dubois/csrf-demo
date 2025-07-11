package co.simplon.p_business.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Value("${p-business.cors.allowedOrigins}")
	private String origins;

	// @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("GET", "POST", "PUT", "OPTIONS")
						.allowedHeaders("X-XSRF-TOKEN", "Content-Type", "Authorization", "Accept")
						.allowCredentials(true);
			}
		};
	}
}