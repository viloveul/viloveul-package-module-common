package com.viloveul.module.common.config;

import com.viloveul.context.auth.AccessControlEvaluator;
import com.viloveul.module.common.service.AccessService;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;

@Configuration(
    proxyBeanMethods = false
)
public class DefaultPermissionEvaluatorRegister {

    @Bean
    public PermissionEvaluator permissionEvaluatorDefault(CacheManager cacheManager, AccessService accessService) {
        return new AccessControlEvaluator(
            cacheManager.getCache("AccessControlEvaluator"),
            accessService::check
        );
    }
}
