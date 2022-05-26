package com.viloveul.module.common.search;

import com.viloveul.module.common.data.entity.Permission;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PermissionSpecification extends SearchSpecification<Permission> {

    @SearchTarget(
        path = {"resource"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String resource;

    @SearchTarget(
        path = {"operation"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String operation;

    @SearchTarget(
        path = {"object"},
        condition = SearchTarget.Condition.EQUAL
    )
    protected String object;

    @SearchTarget(
        path = {"resource","operation","object"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String keyword;

}
