package com.viloveul.module.common.search;

import com.viloveul.module.common.data.entity.Owner;
import com.viloveul.context.filter.SearchSpecification;
import com.viloveul.context.filter.SearchTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OwnerSpecification extends SearchSpecification<Owner> {

    @SearchTarget(
        path = {"resource"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String resource;

    @SearchTarget(
        path = {"user"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String user;

    @SearchTarget(
        path = {"group"},
        condition = SearchTarget.Condition.LIKE
    )
    protected String group;

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
