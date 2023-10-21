package com.cuemymusic.club.service.dataaccess.club.repository;

import com.cuemymusic.club.service.dataaccess.club.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ClubJpaRepository extends JpaRepository<ClubEntity,UUID> {
    @Query(value = "SELECT c.* " +
            "from club c " +
            "inner join devices d on c.id = d.club_id " +
            "inner join ranked_device rd on rd.device_id = d.id " +
            "WHERE rd.user_id = ?1",nativeQuery = true)
    List<ClubEntity> findClubsByUser(UUID userId);

    List<ClubEntity> findClubsByAdminId(UUID adminId);
}
