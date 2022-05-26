package com.viloveul.module.common.search;

import com.viloveul.context.type.VisibilityType;
import com.viloveul.module.common.data.entity.Media;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MediaSpecification extends SearchSpecification<Media> {

    @SearchTarget(
        path = "name",
        condition = SearchTarget.Condition.LIKE
    )
    protected String name;

    @SearchTarget(
        path = "mime",
        condition = SearchTarget.Condition.LIKE
    )
    protected String mime;

    @SearchTarget(
        path = "size",
        condition = SearchTarget.Condition.LIKE,
        option = SearchTarget.Option.SENSITIVE
    )
    protected Long size;

    @SearchTarget(
        path = "visibility",
        condition = SearchTarget.Condition.EQUAL,
        option = SearchTarget.Option.SENSITIVE
    )
    protected VisibilityType visibility;

}
