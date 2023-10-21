package com.cuemymusic.reports.service.dataaccess.token.repository;


import com.cuemymusic.reports.service.dataaccess.token.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<TokenEntity, String> {


    Optional<TokenEntity> findByToken(String token);
}