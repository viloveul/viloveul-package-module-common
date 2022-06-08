package com.viloveul.module.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viloveul.context.ApplicationContainer;
import com.viloveul.context.behaviour.ActivityInterceptor;
import com.viloveul.context.filter.SearchAuthorization;
import com.viloveul.context.filter.SearchAuthorizationArgumentResolver;
import com.viloveul.module.common.data.entity.Owner;
import com.viloveul.module.common.data.entity.Permission;
import com.viloveul.module.common.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.util.List;

@Configuration(
    proxyBeanMethods = false
)
public class WebMvcCommonConfigurer implements WebMvcConfigurer {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
            new ActivityInterceptor(activity -> this.activityService.create(activity), this.objectMapper)
        );
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(
            new SearchAuthorizationArgumentResolver<>(
                new ServletModelAttributeMethodProcessor(true),
                properties -> new SearchAuthorization<>(
                    ApplicationContainer.getContextAuthentication(),
                    new SearchAuthorization.Configuration(Owner.class, Permission.class),
                    properties
                )
            )
        );
    }
}
