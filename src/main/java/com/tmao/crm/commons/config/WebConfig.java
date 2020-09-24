package com.tmao.crm.commons.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJson() {
        return builder -> builder.indentOutput(true).serializationInclusion(Include.NON_NULL);
    }

}
