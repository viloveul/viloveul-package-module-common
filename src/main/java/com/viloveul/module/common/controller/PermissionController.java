package com.viloveul.module.common.controller;

import com.viloveul.module.common.data.entity.Permission;
import com.viloveul.module.common.pojo.PermissionForm;
import com.viloveul.module.common.search.PermissionSpecification;
import com.viloveul.module.common.service.PermissionService;
import com.viloveul.context.util.misc.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${viloveul.controller.permission:/permission}")
public class PermissionController {

    @Autowired(required = false)
    private PermissionEvaluator permissionEvaluator;

    @Autowired
    private PermissionService permissionService;

    @Transactional(readOnly = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('PERMISSION', 'SEARCH')")
    public PageableResult<Permission> search(Pageable pageable, PermissionSpecification filter) {
        return new PageableResult<>(this.permissionService.search(filter, pageable));
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'PERMISSION', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.permissionService.activation(id, status);
    }

    @GetMapping(path = "/{id}")
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission(#id, 'PERMISSION', 'DETAIL')")
    public ResponseEntity<Permission> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.permissionService.detail(id), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasPermission('PERMISSION', 'CREATE')")
    public ResponseEntity<Permission> create(@RequestBody @Valid PermissionForm form) {
        Permission permission = this.permissionService.create(form);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'PERMISSION', 'DELETE')")
    public void delete(@PathVariable("id") String id) {
        this.permissionService.delete(id);
    }
}
