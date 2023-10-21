package com.cuemymusic.club.service.domain.club.ports.impl.application.service.handlers.club.crud;
import com.cuemymusic.club.service.domain.club.dto.club.crud.deleteClub.DeleteClubResponse;
import com.cuemymusic.club.service.domain.club.mapper.club.ClubDataMapper;
import com.cuemymusic.club.service.domain.club.ports.output.repository.club.ClubRepository;
import com.cuemymusic.club.service.domain.club.ports.output.repository.common.UserRepository;
import com.cuemymusic.club.service.domain.exceptions.ClubDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class DeleteClubHandler {

    private final ClubRepository clubRepository;
    private final ClubDataMapper clubDataMapper;
    private final UserRepository userRepository;

    public DeleteClubHandler(ClubRepository clubRepository, ClubDataMapper clubDataMapper, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.clubDataMapper = clubDataMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    public DeleteClubResponse deleteClub(UUID id) {

        return new DeleteClubResponse(delete(id));
    }


    private Boolean delete(UUID userId) throws ClubDomainException {
        try {
            clubRepository.Delete(userId);
            log.info("User with ID: " + userId + " deleted.");
            return true;
        }catch (Exception e){
            throw new ClubDomainException("Failed to Delete User ID: "+ userId);
        }
    }

}
