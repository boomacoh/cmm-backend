package com.cuemymusic.club.service.dataaccess.club.adaptor;

import com.cuemymusic.club.service.dataaccess.club.entity.ClubEntity;
import com.cuemymusic.club.service.dataaccess.club.mapper.ClubDataAccessMapper;
import com.cuemymusic.club.service.dataaccess.club.repository.ClubJpaRepository;
import com.cuemymusic.club.service.dataaccess.device.mapper.DeviceDataAccessMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ClubRepositoryImpl implements ClubRepository {
    private final ClubJpaRepository clubJpaRepository;
    private final ClubDataAccessMapper clubDataAccessMapper;

    public ClubRepositoryImpl(ClubJpaRepository clubJpaRepository,
                              ClubDataAccessMapper clubDataAccessMapper,
                              DeviceDataAccessMapper deviceDataAccessMapper) {
        this.clubJpaRepository = clubJpaRepository;
        this.clubDataAccessMapper = clubDataAccessMapper;
    }

    @Override
    public Club findById(UUID clubId) {
        final ClubEntity clubEntity = clubJpaRepository.findById(clubId).get();
        return clubDataAccessMapper.clubEntityToClub(clubEntity);
    }

    @Override
    public List<Club> findAll() {
        return clubJpaRepository.findAll().stream().map(clubDataAccessMapper::clubEntityToClub).toList();
    }

    @Override
    public List<Club> findByUser(UUID userId) {
        return clubJpaRepository.findClubsByUser(userId).stream().map(clubDataAccessMapper::clubEntityToClub).toList();
    }

    @Override
    public void Delete(UUID clubId) {
        clubJpaRepository.deleteById(clubId);
    }

    @Override
    public Club save(Club club) {
        return clubDataAccessMapper.clubEntityToClub(
            clubJpaRepository.save(
                    clubDataAccessMapper.clubToClubEntity(club)
            )
        );
    }

    @Override
    public Boolean isPresentById(UUID clubId) {
        return clubJpaRepository.findById(clubId).isPresent();
    }

    @Override
    public List<Club> findManagedByUser(UUID userId) {
        return clubJpaRepository.findClubsByAdminId(userId).stream().map(clubDataAccessMapper::clubEntityToClub).toList();
    }




}
