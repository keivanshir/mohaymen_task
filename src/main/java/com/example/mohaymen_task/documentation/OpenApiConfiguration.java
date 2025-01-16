package com.example.mohaymen_task.documentation;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfiguration {
    @Bean
    public OpenAPI parkingSystemManagementOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction management APIs")
                        .contact(new Contact().name("Keivan Shirkoubian")
                                .email("keivan.shir.74@gmail.com")
                                .url("https://github.com/keivanshir/mohaymen_task"))
                        .description("This is a transaction management system API documentation")
                        .version("1.0.0"));
    }
}
