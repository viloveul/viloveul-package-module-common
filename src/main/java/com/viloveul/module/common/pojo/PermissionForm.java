package com.viloveul.module.common.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PermissionForm implements Serializable {

    @NotNull
    @NotEmpty
    private String user;

    @NotNull
    @NotEmpty
    private String resource;

    @NotNull
    @NotEmpty
    private String operation;

    @NotNull
    @NotEmpty
    private String object;

    public PermissionForm(String user, String resource, String operation, String object) {
        this.user = user;
        this.resource = resource;
        this.operation = operation;
        this.object = object;
    }

}
