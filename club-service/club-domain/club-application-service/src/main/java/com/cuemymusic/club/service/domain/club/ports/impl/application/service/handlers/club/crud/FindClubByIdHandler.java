package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.crud;

import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FindClubByIdHandler {
    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;

    public FindClubByIdHandler(ClubRepository clubRepository, ClubDataMapper clubDataMapper) {
        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
    }


    public ClubDTO find(UUID id) {
        if(!clubRepository.isPresentById(id)) {
            throw new ClubDomainException("Club doesn't exist");
        }
        Club club =  clubRepository.findById(id);
        return clubDataMapper.clubToClubDTO(club);
    }

}
