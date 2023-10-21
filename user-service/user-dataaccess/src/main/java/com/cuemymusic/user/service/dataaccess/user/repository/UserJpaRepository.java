package com.cuemymusic.user.service.dataaccess.user.repository;

import com.cuemymusic.user.service.dataaccess.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);


    @Query(value = "Select \n" +
            " _u.*\n" +
            "from users _u \n" +
            "inner join ranked_device _rd \n" +
            "on _u.id = _rd.user_id\n" +
            "inner join devices _d\n" +
            "on _rd.device_id = _d.id\n" +
            "inner join club _c\n" +
            "on _c.id = _d.club_id\n" +
            "where _c.id = ?2\n" +
            "and _u.role != 1\n" +
            "and _c.admin_id = ?1"
    ,nativeQuery = true)
    List<UserEntity> findAllMyUsersByClubId(UUID admin,UUID clubId);
    @Query(value = "Select distinct _u.* \n" +
            "from users _u \n" +
            "inner join ranked_device _rd\n" +
            "on _u.id = _rd.user_id\n" +
            "inner join devices _d\n" +
            "on _rd.device_id = _d.id\n" +
            "inner join club _c\n" +
            "on _c.id = _d.club_id\n" +
            "where _c.id in (\n" +
            "\tSELECT _c.id\n" +
            "\tfrom users _u \n" +
            "\tinner join ranked_device _rd\n" +
            "\ton _u.id = _rd.user_id\n" +
            "\tinner join devices _d\n" +
            "\ton _rd.device_id = _d.id\n" +
            "\tinner join club _c\n" +
            "\ton _c.id = _d.club_id\n" +
            "\twhere _u.id = ?1\n" +
            ")\n" +
            "and role in (2,3)"
            ,nativeQuery = true)
    List<UserEntity> findMyManagers(UUID user);
    @Query(value = "Select \n" +
            " _u.*\n" +
            "from users _u \n" +
            "inner join ranked_device _rd \n" +
            "on _u.id = _rd.user_id\n" +
            "inner join devices _d\n" +
            "on _rd.device_id = _d.id\n" +
            "inner join club _c\n" +
            "on _c.id = _d.club_id\n" +
            "where _c.id = ?3\n" +
            "and _c.admin_id = ?1\n" +
            "and _u.role != 1\n" +
            "and _u.id = ?2"
            ,nativeQuery = true)
    Optional<UserEntity> findUserByClubId(UUID admin, UUID user,UUID clubId);

    @Modifying
    @Query(value = "Insert Into coach_club(user_id, club_id) Values (?1,?2)",nativeQuery = true)
    void assignUser(UUID idUser, UUID clubId);
    @Modifying
    @Query(value = "UPDATE public.users\n" +
            "\tSET  role = 0\n" +
            "\tWHERE id in (SELECT user_id FROM coach_club WHERE club_id = ?1);",nativeQuery = true)
    void setUserByClubId(UUID clubId);
    @Modifying
    @Query(value = "Delete From coach_club where club_id = ?1",nativeQuery = true)
    void revokeAllUsersByClubId(UUID clubId);
    @Modifying
    @Query(value = "Delete From coach_club where user_id = ?1 and club_id = ?2",nativeQuery = true)
    void revokeUser(UUID idUser, UUID clubId);
    @Modifying
    @Query(value = "Delete From ranked_device where user_id = ?1",nativeQuery = true)
    void deleteAllDevices(UUID userId);
    @Modifying
    @Query(value = "Delete From ranked_device where user_id = ?1 and rank = ?2",nativeQuery = true)
    void deleteDevice(UUID userId, Integer rank);

}
