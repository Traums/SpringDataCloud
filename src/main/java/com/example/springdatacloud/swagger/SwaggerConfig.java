package com.example.springdatacloud.swagger;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Swagger").version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .group("http")
                .pathsToMatch("/**")
                .build();
    }
}
//@Operation - Описывает операцию или обычно метод HTTP для определенного пути.
//@Parameter - Представляет один параметр в операции OpenAPI.
//@RequestBody - Представляет тело запроса в операции
//@ApiResponse - Представляет ответ в операции
//@Tag - Представляет теги для операции или определения OpenAPI.
//@Server - Представляет серверы для операции или для определения OpenAPI.
//@Callback - Описывает набор запросов
//@Link - Представляет возможную ссылку времени разработки для ответа.
//@Schema - Позволяет определять входные и выходные данные.
//@ArraySchema - Позволяет определять входные и выходные данные для типов массивов.
//@Content - Предоставляет схему и примеры для определенного типа мультимедиа.
//@Hidden - Скрывает ресурс, операцию или свойство
