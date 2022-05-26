package com.viloveul.module.common.listener;

import com.viloveul.context.ApplicationContainer;
import com.viloveul.context.event.entity.SavedEvent;
import com.viloveul.context.util.helper.FieldHelper;
import com.viloveul.context.auth.AccessControl;
import com.viloveul.context.auth.dto.DetailAuthentication;
import com.viloveul.module.common.pojo.OwnerForm;
import com.viloveul.module.common.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EntityOwnerCreation implements Serializable {

    @Autowired
    private transient OwnerService ownerService;

    @EventListener(condition = "#event.action == 'CREATED'")
    public void createOwner(SavedEvent event) {
        if (event.getEntity().getClass().isAnnotationPresent(AccessControl.class)) {
            DetailAuthentication authentication = ApplicationContainer.getDetailAuthentication(DetailAuthentication.class);
            AccessControl permissionChecker = event.getEntity().getClass().getAnnotation(AccessControl.class);
            if (authentication != null && permissionChecker.owner()) {
                String resource = permissionChecker.resource();
                String id = FieldHelper.fieldValue(event.getEntity(), permissionChecker.identity(), String.class);
                OwnerForm form = new OwnerForm();
                form.setObject(id);
                form.setResource(resource);
                form.setUser(authentication.getId());
                form.setGroup(authentication.getGroup().getId());
                this.ownerService.create(form);
            }
        }
    }
}
