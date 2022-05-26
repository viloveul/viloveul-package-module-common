package com.viloveul.module.common.data.repository;

import com.viloveul.module.common.data.entity.Setting;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, String> {

    @Cacheable(value = "setting_valkeys", key = "#key")
    @Query("SELECT x.value FROM Setting x WHERE x.key = ?1 AND x.status = true")
    String getValueByKey(String key);

}
