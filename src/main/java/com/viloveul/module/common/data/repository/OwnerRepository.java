package com.viloveul.module.common.data.repository;

import com.viloveul.module.common.data.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String>, JpaSpecificationExecutor<Owner> {

    @Query(
        "SELECT CASE WHEN count(x) > 0 THEN TRUE ELSE FALSE END " +
        "FROM Owner x " +
        "WHERE x.user = :user " +
        "AND x.group = :group " +
        "AND x.resource = :resource " +
        "AND x.object = :object " +
        "AND x.status = TRUE "
    )
    boolean check(
        @Param("user") String user,
        @Param("group") String group,
        @Param("resource") String resource,
        @Param("object") String object
    );

    @Query(
        "SELECT CASE WHEN count(x) > 0 THEN TRUE ELSE FALSE END " +
        "FROM Owner x " +
        "WHERE x.group IN :groups " +
        "AND x.resource = :resource " +
        "AND x.object = :object " +
        "AND x.status = TRUE "
    )
    boolean check(
        @Param("groups") List<String> groups,
        @Param("resource") String resource,
        @Param("object") String object
    );

}
