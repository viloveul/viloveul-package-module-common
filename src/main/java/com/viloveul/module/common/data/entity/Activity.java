package com.viloveul.module.common.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.viloveul.context.base.AbstractEntity;
import com.viloveul.context.auth.AccessControl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tprefix_activity", schema = "schema")
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AccessControl(resource = "ACTIVITY")
public class Activity extends AbstractEntity {

    @Column(name = "url")
    @JsonProperty("url")
    private String url;

    @Column(name = "ua")
    @JsonProperty("user_agent")
    private String userAgent;

    @Column(name = "ip")
    @JsonProperty("ip_address")
    private String ipAddress;

    @Column(name = "reference")
    @JsonProperty("reference")
    private String reference;

    @Column(name = "action")
    @JsonProperty("action")
    private String action;

    @Column(name = "payload")
    @JsonProperty("payload")
    private String payload;

    public Activity(String url, String action, String userAgent, String ipAddress) {
        this.url = url;
        this.action = action;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
    }
}
