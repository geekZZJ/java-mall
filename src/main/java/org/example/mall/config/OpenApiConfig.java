package org.example.mall.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // 访问 http://localhost:8080/swagger-ui.html 可以看到 API 文档
    @Bean
    public OpenAPI mallOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("慕慕生鲜")
                        .description("mall 接口文档")
                        .version("1.0"));
    }
}
