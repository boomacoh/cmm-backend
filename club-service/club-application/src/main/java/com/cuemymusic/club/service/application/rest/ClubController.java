package com.cuemymusic.club.service.application.rest;

import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.createClub.CreateClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.deleteClub.DeleteClubResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubDTO;
import com.cuemymusic.club.service.domain.club.dto.club.crud.queryClubs.ClubListResponse;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubCommand;
import com.cuemymusic.club.service.domain.club.dto.club.crud.updateClub.UpdateClubResponse;
import com.cuemymusic.club.service.domain.club.ports.input.service.ClubApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/clubs", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class ClubController {
    private final ClubApplicationService clubApplicationService;
    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<CreateClubResponse> createUser(@RequestBody CreateClubCommand createClubCommand){
        log.info("Creating User with email : {} ",createClubCommand.email());
        CreateClubResponse createClubResponse = clubApplicationService.createClub(createClubCommand);
        return ResponseEntity.ok(createClubResponse);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ClubListResponse> findAll(){
        ClubListResponse clubListResponse = clubApplicationService.findAll();
        return ResponseEntity.ok(clubListResponse);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ClubDTO> findById(@PathVariable("id") UUID id){
        ClubDTO clubDTO = clubApplicationService.findById(id);
        return ResponseEntity.ok(clubDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<DeleteClubResponse> deleteUserById(@PathVariable("id") UUID id){
        DeleteClubResponse deleteClubResponse = clubApplicationService.deleteClub(id);
        return ResponseEntity.ok(deleteClubResponse);
    }
    @PutMapping("/")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<UpdateClubResponse> updateUser(@RequestBody UpdateClubCommand updateClubCommand){
        UpdateClubResponse updateClubResponse = clubApplicationService.updateClub(updateClubCommand);
        return ResponseEntity.ok(updateClubResponse);
    }

}
