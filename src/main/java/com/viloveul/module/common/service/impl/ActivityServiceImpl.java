package com.viloveul.module.common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viloveul.module.common.data.entity.Activity;
import com.viloveul.module.common.data.repository.ActivityRepository;
import com.viloveul.module.common.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Page<Activity> search(Specification<Activity> filter, Pageable pageable) {
        return this.activityRepository.findAll(filter, pageable);
    }

    @Override
    public Boolean create(Map<String, String> map) {
        Activity activity = this.objectMapper.convertValue(map, Activity.class);
        this.create(activity);
        return true;
    }

    @Override
    public Activity create(Activity activity) {
        return this.activityRepository.save(activity);
    }
}
