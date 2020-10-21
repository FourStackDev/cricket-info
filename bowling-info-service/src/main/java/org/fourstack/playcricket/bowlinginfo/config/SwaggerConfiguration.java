package org.fourstack.playcricket.bowlinginfo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Autowired
	private Optional<BuildProperties> buildProperties;
	
	@Bean
	public Docket productApi() {
		String version = "1.0.0";
		if (buildProperties.isPresent())
			version = buildProperties.get().getVersion();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo(version))
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.fourstack.playcricket.bowlinginfo.controllers"))
				.paths(PathSelectors.regex("/api.*"))
				.build();
	}
	
	private ApiInfo getApiInfo(String version) {
		return new ApiInfoBuilder().title("Bowling Info Service")
				.description("Bowling Service")
				.version(version)
				.build();
	}
}
