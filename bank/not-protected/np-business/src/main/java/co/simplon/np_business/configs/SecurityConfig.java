package co.simplon.np_business.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // désactive CSRF pour l'API REST
				.cors(cors -> cors.disable()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		return http.build();
	}
}
