package com.service.cloudtea.config;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Thiết lập các server dùng để test api
                .servers(Lists.newArrayList(
                        new Server().url("http://localhost:8080")
                ))
                // info
                .info(new Info().title("Cloud Tea")
                        .description("API backend for store Cloud Tea")
                        .contact(new Contact()
                                .email("test@gmail.com")
                                .name("CloudTea")
                                .url("https://test.com")));
    }
}
