package com.cuemymusic.user.service.application.rest;

import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.delete.DeleteUserResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.find.by.id.FindUserByIdResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserResponse;
import com.cuemymusic.user.service.domain.ports.input.service.UserApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/users", produces = "application/vnd.api.v1+json")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;
    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserCommand createUserCommand){
        log.info("Creating User with email : {} ",createUserCommand.email());
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        return ResponseEntity.ok(createUserResponse);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<FindUsersResponse> findAll(){
        FindUsersResponse findUsersResponse = userApplicationService.findAll();
        return ResponseEntity.ok(findUsersResponse);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<FindUserByIdResponse> findById(@PathVariable("id") UUID id){
        FindUserByIdResponse findUsersResponse = userApplicationService.findUserById(id);
        return ResponseEntity.ok(findUsersResponse);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<DeleteUserResponse> deleteUserById(@PathVariable("id") UUID id){
        DeleteUserResponse deleteUserResponse = userApplicationService.deleteUserByAdmin(id);
        return ResponseEntity.ok(deleteUserResponse);
    }
    @PutMapping("/")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<UpdateUserResponse> updateUser(@RequestBody UpdateUserCommand updateUserCommand){
        UpdateUserResponse deleteUserResponse = userApplicationService.updateUserByAdmin(updateUserCommand);
        return ResponseEntity.ok(deleteUserResponse);
    }
    
    
 
}
