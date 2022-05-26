package com.viloveul.module.common.pojo;

import com.viloveul.context.type.VisibilityType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MediaForm implements Serializable {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String source;

    private VisibilityType visibility = VisibilityType.PUBLIC;

}
