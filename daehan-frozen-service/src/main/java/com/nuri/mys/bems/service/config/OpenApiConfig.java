package com.nuri.mys.bems.service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {
    @Value("${swagger.api.title}")
    private String apiTitle;
    @Value("${swagger.contact.name}")
    private String contName;
    @Value("${swagger.contact.url}")
    private String contUrl;
    @Value("${swagger.contact.email}")
    private String contEmail;
    @Value("${server.port}")
    private String port;
//    @Value("${swagger.doc.desc}")
//    private String apiDocDesc;
//    @Value("${swagger.doc.path}")
//    private String apiDocPath;

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info().title(apiTitle).version(appVersion)
                .contact(new Contact().name(contName).url(contUrl).email(contEmail))
                ;
        SecurityScheme tokenScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        SecurityRequirement schemaRequirement = new SecurityRequirement().addList("bearerAuth").addList("userAuth");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components().addSecuritySchemes("bearerAuth",tokenScheme))
                .security(Arrays.asList(schemaRequirement))
                .info(info)
                ;
    }
}
