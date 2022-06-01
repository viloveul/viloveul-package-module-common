package com.viloveul.module.common.data.entity;

import com.viloveul.context.base.AbstractMidEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import com.viloveul.context.auth.model.PermissionModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tbl_permission", schema = "schema")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "PERMISSION")
public class Permission extends AbstractMidEntity implements PermissionModel {

    @Column(name = "id_user")
    private String user;

    @Column(name = "resource", updatable = false)
    private String resource;

    @Column(name = "operation")
    private String operation;

    @Column(name = "object", updatable = false)
    private String object;

    public Permission(String user, String resource, String operation, String object) {
        this.user = user;
        this.resource = resource;
        this.operation = operation;
        this.object = object;
    }
}
