package com.viloveul.module.common.service;

import com.viloveul.module.common.data.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ActivityService {

    Boolean create(Map<String, String> map);

    Activity create(Activity activity);

    Page<Activity> search(Specification<Activity> filter, Pageable pageable);
}
