package com.cuemymusic.user.service.dataaccess.token.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cuemymusic.user.service.dataaccess.token.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenJpaRepository extends JpaRepository<TokenEntity, String> {

    @Query(value = """
      select t from com.cuemymusic.user.service.dataaccess.token.entity.TokenEntity t inner join com.cuemymusic.user.service.dataaccess.user.entity.UserEntity u\s
      on t.user.id = u.id\s
      where u.id = ?1 and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenEntity> findAllValidTokenByUser(UUID id);

    Optional<TokenEntity> findByToken(String token);
}