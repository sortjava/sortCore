package com.sort.sortcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class ServiceApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	/*@Bean
	public Docket docket() {
		return (new Docket(DocumentationType.SWAGGER_2)).select()
				.apis(RequestHandlerSelectors.basePackage("com.sort.sortcore")).paths(PathSelectors.any()).build()
				.apiInfo(this.apiInfo());
	}*/

	private ApiInfo apiInfo() {
		return (new ApiInfoBuilder()).title("Sort API Reference")
				.version(this.getClass().getPackage().getImplementationVersion()).build();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class[] { ServiceApplication.class });
	}
}