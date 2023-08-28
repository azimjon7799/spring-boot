package com.clean.code.springboot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@OpenAPIDefinition
@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI baseOpenApi(){
        ApiResponse badRequestAPI = new ApiResponse().content(
             new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                     new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                             new Example().value("{\"code\":400, \"status\":\"Bad Request!\", \"Message\":\"Bad Request!\"}"))
                     )
        );

        ApiResponse internalServerErrorAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\":500, \"status\":\"Internal Server Error!\", \"Message\":\"Internal Server Error!\"}"))
                )
        );

        Components components = new Components();
        components.addResponses("badRequest", badRequestAPI);
        components.addResponses("internalServerError", internalServerErrorAPI);

        return new OpenAPI()
                .components(components)
                .info(new Info()
                    .title("Spring-boot Doc")
                    .version("1.0.0")
                    .description("Java, Spring-boot, Spring-security, Jpa, Postgres, Swagger")
        );
    }
}
