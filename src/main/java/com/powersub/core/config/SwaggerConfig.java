package com.powersub.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi publicUsersApi() {
    String[] paths = {"/**/registration/**"};
    return GroupedOpenApi.builder()
            .group("registration")
            .pathsToMatch(paths)
            .build();
  }

  @Bean
  public GroupedOpenApi allApi(){
    String[] paths = {"/**"};
    return GroupedOpenApi.builder()
            .group("all")
            .pathsToMatch(paths)
            .build();
  }

  @Bean
  public OpenAPI customOpenApi(@Value("This documentation who wants to make life easier")String appDescription,
                               @Value("0.0.1")String appVersion) {
    return new OpenAPI().info(new Info().title("PowerSub API")
                    .version(appVersion)
                    .description(appDescription)
                    .license(new License().name("Apache 2.0")
                            .url("http://springdoc.org"))
                    .contact(new Contact().name("Go to google")
                            .url("https://www.google.com")));
  }
}
