package com.viloveul.module.common.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AccessService {

    Boolean check(Authentication authentication, String resource, String operation, String object);
}
