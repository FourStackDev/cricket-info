package org.fourstack.playcricket.playerinfo.config;

import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {
	@Autowired
	Optional<BuildProperties> build;

	@Bean
	public Docket productApi() {
		String version = "1.0.0";
		if (build.isPresent())
			version = build.get().getVersion();

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo(version))
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.fourstack.playcricket.playerinfo.controllers"))
				.paths(apiPaths())
				.build();
	}

	private ApiInfo apiInfo(String version) {
		Contact contact = new Contact("Manjunath HM", "http://dummy.org", "fourstackdevelopers@gmail.com");
		return new ApiInfoBuilder().title("Player Information Service")
				.description("Player Info")
				.version(version)
				.contact(contact)
				.build();
	}

	private Predicate<String> apiPaths() {
		return PathSelectors.regex("/api/v1/player-info.*")
				.or(PathSelectors.regex("/api/internal/player-info.*"));
	}
}
