package com.viloveul.module.common.service.impl;

import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.constant.message.DomainErrorCollection;
import com.viloveul.module.common.data.entity.Owner;
import com.viloveul.module.common.pojo.OwnerForm;
import com.viloveul.module.common.data.repository.OwnerRepository;
import com.viloveul.module.common.service.OwnerService;
import com.viloveul.context.exception.GeneralFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class OwnerServiceImpl extends AbstractComponent implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner update(String id, @Valid OwnerForm form) {
        Owner owner = this.ownerRepository.getOne(id);
        owner.setGroup(form.getGroup());
        owner.setUser(form.getUser());
        return this.ownerRepository.save(owner);
    }

    @Override
    public Owner create(@Valid OwnerForm form) {
        return this.ownerRepository.save(
            new Owner(
                form.getUser(),
                form.getGroup(),
                form.getResource(),
                form.getObject())
        );
    }

    @Override
    public Owner detail(String id) {
        Optional<Owner> result = this.ownerRepository.findById(id);
        if (!result.isPresent()) {
            throw new GeneralFailureException(DomainErrorCollection.DATA_IS_NOT_EXISTS);
        }
        return result.get();
    }

    @Override
    public void activation(String id, Boolean status) {
        Owner owner = this.ownerRepository.getOne(id);
        owner.setStatus(status);
        this.ownerRepository.save(owner);
    }

    @Override
    public Page<Owner> search(Specification<Owner> filter, Pageable pageable) {
        return this.ownerRepository.findAll(filter, pageable);
    }
}
