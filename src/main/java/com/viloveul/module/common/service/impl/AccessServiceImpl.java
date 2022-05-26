package com.viloveul.module.common.service.impl;

import com.viloveul.context.auth.dto.DetailAuthentication;
import com.viloveul.module.common.data.repository.OwnerRepository;
import com.viloveul.module.common.data.repository.PermissionRepository;
import com.viloveul.module.common.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Boolean check(Authentication authentication, String resource, String operation, String object) {
        String authority = operation.concat("-").concat(resource).toUpperCase(Locale.getDefault());
        DetailAuthentication auth = (DetailAuthentication) authentication.getDetails();
        boolean result = false;
        if (this.permissionRepository.check(auth.getId(), resource, operation, object)) {
            result = true;
        } else if (auth.getAccessors().contains(authority) && this.ownerRepository.check(auth.getId(), auth.getGroup().getId(), resource, object)) {
            result = true;
        } else {
            List<String> groups = new ArrayList<>();
            for (DetailAuthentication.GroupMapper group : auth.getAbilities()) {
                if (group.getAuthorities().contains(authority)) {
                    groups.addAll(group.getGroups());
                }
            }
            if (!groups.isEmpty()) {
                result = this.ownerRepository.check(groups, resource, object);
            }
        }
        return result;
    }
}
