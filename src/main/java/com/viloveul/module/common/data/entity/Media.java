package com.viloveul.module.common.data.entity;

import com.viloveul.context.base.AbstractEntity;
import com.viloveul.context.type.VisibilityType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tbl_media", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AccessControl(resource = "MEDIA", owner = true)
public class Media extends AbstractEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(Media.class);

    @Column(name = "source")
    private String source;

    @Column(name = "name")
    private String name;

    @Column(name = "mime")
    private String mime;

    @Column(name = "size")
    private Long size;

    @Column(name = "visibility")
    @Enumerated(EnumType.STRING)
    private VisibilityType visibility;

    public Media(String name, String source, String mime, Long size, VisibilityType visibility) {
        this.name = name;
        this.source = source;
        this.mime = mime;
        this.size = size;
        this.visibility = visibility;
    }

    public Media(String name, String source, String mime, Long size) {
        this.name = name;
        this.source = source;
        this.mime = mime;
        this.size = size;
    }

    @PrePersist
    @Override
    public void onBeforeCreate() {
        super.onBeforeCreate();
        if (this.visibility == null) {
            this.visibility = VisibilityType.PUBLIC;
        }
    }
}
