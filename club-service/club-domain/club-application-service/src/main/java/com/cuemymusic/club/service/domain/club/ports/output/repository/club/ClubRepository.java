package com.cuemymusic.club.service.domain.club.ports.output.repository.club;


import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClubRepository {
    Club findById(UUID clubId);
    List<Club> findAll();
    List<Club> findByUser(UUID userId);
    void Delete(UUID clubId);
    Club save(Club club);
    Boolean isPresentById(UUID clubId);

    List<Club> findManagedByUser(UUID userId);
}
