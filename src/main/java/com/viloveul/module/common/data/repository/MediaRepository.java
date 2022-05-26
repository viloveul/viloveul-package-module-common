package com.viloveul.module.common.data.repository;

import com.viloveul.module.common.data.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, String>, JpaSpecificationExecutor<Media> {
}
