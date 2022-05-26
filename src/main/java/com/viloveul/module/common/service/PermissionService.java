package com.viloveul.module.common.service;

import com.viloveul.module.common.data.entity.Permission;
import com.viloveul.module.common.pojo.PermissionForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public interface PermissionService {

    Permission create(PermissionForm form);

    void activation(String id, Boolean status);

    void delete(String id);

    Permission detail(String id);

    void handle(Consumer<PermissionService> consumer);

    Page<Permission> search(Specification<Permission> filter, Pageable pageable);
}
