package br.com.mcos.pockotlinspringboot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun estudoApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.mcos"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData())

    }

    private fun metaData(): ApiInfo {
        return ApiInfoBuilder()
            .title("Documentação - POC Kotlin")
            .description("API Rest com Kotlin e Spring Boot")
            .version("1.0.0")
            .build()
    }
}