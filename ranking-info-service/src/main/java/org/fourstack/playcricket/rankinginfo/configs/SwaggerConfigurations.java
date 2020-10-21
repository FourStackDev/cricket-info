package org.fourstack.playcricket.rankinginfo.configs;

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
				.apis(RequestHandlerSelectors.basePackage("org.fourstack.playcricket.rankinginfo.controllers"))
				.paths(PathSelectors.regex("/api/v1/icc-rankings.*"))
				.build();
	}

	private ApiInfo apiInfo(String version) {
		return new ApiInfoBuilder().title("ICC Ranking Information Service").description("ICC ranking Info")
				.version(version).build();
	}
}
