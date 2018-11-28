package com.xiao.gs.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.List;

import static com.xiao.gs.bind.convert.ExcelHttpMessageConverter.EXCEL_MEDIA_TYPE;

/**
 * @author luoxiaoxiao
 */
@Configuration
public class ExportConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {
        // Define the view resolvers
        ViewResolver beanNameViewResolver = new BeanNameViewResolver();
        List<ViewResolver> resolvers = Lists.newArrayList(beanNameViewResolver);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useRegisteredExtensionsOnly(false)
                .favorParameter(true)
                .parameterName("format")
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xls", EXCEL_MEDIA_TYPE);
    }

}
