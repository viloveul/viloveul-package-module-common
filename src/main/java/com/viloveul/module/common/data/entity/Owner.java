package com.viloveul.module.common.data.entity;

import com.viloveul.context.base.AbstractMidEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import com.viloveul.context.auth.model.OwnerModel;
import lombok.EqualsAndHashCode;
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
@Table(name = "tprefix_owner", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AccessControl(resource = "OWNER")
public class Owner extends AbstractMidEntity implements OwnerModel {

    @Column(name = "id_user")
    private String user;

    @Column(name = "id_group")
    private String group;

    @Column(name = "resource", updatable = false)
    private String resource;

    @Column(name = "object", updatable = false)
    private String object;

    public Owner(String user, String group, String resource, String object) {
        this.user = user;
        this.group = group;
        this.resource = resource;
        this.object = object;
    }

}
