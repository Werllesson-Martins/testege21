package br.com.testege21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class CorsConfiguration {

	// Liberar o acesso para requisições de servidores externos para não ser
	// bloqueado pela politica de segurança do CORS ORIGIN
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Tipos de requisições que serão aceitas pela API
				registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH");
			}
		};
	}
}
