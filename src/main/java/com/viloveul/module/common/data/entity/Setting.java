package com.viloveul.module.common.data.entity;

import com.viloveul.context.base.AbstractMidEntity;
import com.viloveul.context.type.VisibilityType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tprefix_setting", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Setting extends AbstractMidEntity {

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "visibility")
    @Enumerated(EnumType.STRING)
    private VisibilityType visibility;

    public Setting(String key, String value) {
        this.setKey(key);
        this.setValue(value);
        this.setVisibility(VisibilityType.PUBLIC);
    }
}
