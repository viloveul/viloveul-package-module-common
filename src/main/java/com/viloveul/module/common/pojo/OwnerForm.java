package com.viloveul.module.common.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class OwnerForm implements Serializable {

    @NotNull
    @NotEmpty
    private String user;

    @NotNull
    @NotEmpty
    private String group;

    @NotNull
    @NotEmpty
    private String resource;

    @NotNull
    @NotEmpty
    private String object;

    public OwnerForm(String user, String group, String resource, String object) {
        this.user = user;
        this.group = group;
        this.resource = resource;
        this.object = object;
    }
}
