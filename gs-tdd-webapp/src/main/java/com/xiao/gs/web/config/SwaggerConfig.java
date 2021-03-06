package com.xiao.gs.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author luoxiaoxiao
 */
@Profile("swagger")
@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("all-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试驱动开发模式项目接口文档")
                .description("Swagger API开发探索之路")
                .contact(new Contact("luoxiaoxiao", "https://github.com/2345free", "3060928836@qq.com"))
                .version("1.0")
                .build();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("", "/swagger-ui.html");
    }

}
