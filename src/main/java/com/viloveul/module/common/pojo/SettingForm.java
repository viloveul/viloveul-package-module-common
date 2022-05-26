package com.viloveul.module.common.pojo;

import com.viloveul.context.type.VisibilityType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class SettingForm implements Serializable {

    private String key;

    private String value;

    private VisibilityType visibility = VisibilityType.PUBLIC;

}
