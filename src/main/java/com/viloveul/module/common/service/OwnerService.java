package com.viloveul.module.common.service;

import com.viloveul.module.common.data.entity.Owner;
import com.viloveul.module.common.pojo.OwnerForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {

    Owner update(String id, OwnerForm form);

    Owner create(OwnerForm form);

    Owner detail(String id);

    void activation(String id, Boolean status);

    Page<Owner> search(Specification<Owner> filter, Pageable pageable);
}
