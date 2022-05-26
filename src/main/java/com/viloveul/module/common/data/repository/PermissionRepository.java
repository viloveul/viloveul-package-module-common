package com.viloveul.module.common.data.repository;

import com.viloveul.module.common.data.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

    @Query(
        "SELECT CASE WHEN count(x) > 0 THEN TRUE ELSE FALSE END " +
        "FROM Permission x " +
        "WHERE x.user = :user " +
        "AND x.resource = :resource " +
        "AND x.operation = :operation " +
        "AND x.object = :object " +
        "AND x.status = TRUE "
    )
    boolean check(
        @Param("user") String user,
        @Param("resource") String resource,
        @Param("operation") String operation,
        @Param("object") String object
    );

}
