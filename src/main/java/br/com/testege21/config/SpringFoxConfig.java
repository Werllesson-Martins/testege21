package br.com.testege21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.testege21.controller")).paths(PathSelectors.any())
				.build().apiInfo(apinInfo())
				.tags(new Tag("Geo", "Recebe as informações geograficas e retorna a localidade"));
	}

	public ApiInfo apinInfo() {
		return new ApiInfoBuilder().title("Grupo GE21").description("Api para consulta de dados geográficos do Google")
				.version("1.0").contact(new Contact("Grupo GE21", "https://www.grupoge21.com", "rh@grupoge21.com"))
				.build();
	}

}
