package com.viloveul.module.common.service.impl;

import com.viloveul.module.common.service.PermissionService;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.common.data.entity.Permission;
import com.viloveul.module.common.pojo.PermissionForm;
import com.viloveul.module.common.data.repository.PermissionRepository;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission create(@Valid PermissionForm form) {
        return this.permissionRepository.save(
            new Permission(
                form.getUser(),
                form.getResource(),
                form.getOperation(),
                form.getObject()
            )
        );
    }

    @Override
    public void activation(String id, Boolean status) {
        Permission permission = this.permissionRepository.getOne(id);
        permission.setStatus(status);
        this.permissionRepository.save(permission);
    }

    @Override
    public void delete(String id) {
        this.permissionRepository.deleteById(id);
    }

    @Override
    public Permission detail(String id) {
        Optional<Permission> result = this.permissionRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Async
    @Override
    public void handle(Consumer<PermissionService> consumer) {
        consumer.accept(this);
    }

    @Override
    public Page<Permission> search(Specification<Permission> filter, Pageable pageable) {
        return this.permissionRepository.findAll(filter, pageable);
    }
}
