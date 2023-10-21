package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.crud;

import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;

import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FindClubsHandler {
    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final UserRepository userRepository;



    public FindClubsHandler(
            ClubRepository clubRepository, ClubDataMapper clubDataMapper, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
        this.userRepository = userRepository;
    }

    public ClubListResponse findAll() {
        return new ClubListResponse( clubRepository.findAll().stream().map(e-> {
            User user = userRepository.findById(e.getAdminId().getValue());
            ClubDTO.ClubDTOBuilder clubDto =  clubDataMapper.clubToClubDTOBuilder(e);
            return  clubDto.adminEmail(user.getEmail()).build();
        }).toList());
    }


}
