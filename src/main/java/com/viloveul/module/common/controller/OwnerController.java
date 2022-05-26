package com.viloveul.module.common.controller;

import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.context.exception.GeneralFailureException;
import com.viloveul.module.common.data.entity.Owner;
import com.viloveul.module.common.pojo.OwnerForm;
import com.viloveul.module.common.search.OwnerSpecification;
import com.viloveul.module.common.service.OwnerService;
import com.viloveul.context.util.misc.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    @PreAuthorize("hasPermission('OWNER', 'SEARCH')")
    public PageableResult<Owner> search(OwnerSpecification filter, Pageable pageable) {
        return new PageableResult<>(this.ownerService.search(filter, pageable));
    }

    @Transactional(readOnly = true)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission(#id, 'OWNER', 'DETAIL')")
    public ResponseEntity<Owner> detail(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.ownerService.detail(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasPermission(#id, 'OWNER', 'UPDATE')")
    public ResponseEntity<Owner> update(@PathVariable("id") String id, @RequestBody @Valid OwnerForm form) {
        Owner owner = this.ownerService.update(id, form);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } else {
            throw new GeneralFailureException(DomainErrorCollection.DATA_CANT_BE_UPDATED);
        }
    }

    @Transactional
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasPermission('OWNER', 'CREATE')")
    public ResponseEntity<Owner> create(@RequestBody @Valid OwnerForm form) {
        Owner owner = this.ownerService.create(form);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.CREATED);
        } else {
            throw new GeneralFailureException(DomainErrorCollection.DATA_CANT_BE_CREATED);
        }
    }

    @Transactional
    @PostMapping(path = "/{id}/activation/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasPermission(#id, 'OWNER', 'ACTIVATION')")
    public void activation(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        this.ownerService.activation(id, status);
    }

}
