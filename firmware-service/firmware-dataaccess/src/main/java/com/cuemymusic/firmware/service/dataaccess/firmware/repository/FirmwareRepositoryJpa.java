package com.cuemymusic.firmware.service.dataaccess.firmware.repository;

import com.cuemymusic.firmware.service.dataaccess.firmware.entity.FirmwareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface FirmwareRepositoryJpa extends JpaRepository<FirmwareEntity, UUID> {
    List<FirmwareEntity> findFirmwaresByEnabled(Boolean enabled);
    @Modifying
    @Query(value = "UPDATE firmware SET enabled = (id = ?1);",nativeQuery = true)
    void promoteById(UUID id);
}
