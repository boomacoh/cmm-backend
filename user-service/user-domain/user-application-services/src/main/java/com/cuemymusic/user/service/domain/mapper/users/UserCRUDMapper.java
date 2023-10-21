package com.cuemymusic.user.service.domain.mapper.users;

import com.cuemymusic.user.service.domain.dto.user.adminstration.delete.DeleteUserResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.update.UpdateUserResponse;

import com.cuemymusic.user.service.domain.dto.user.common.DeviceDTO;
import com.cuemymusic.user.service.domain.dto.user.common.UserDTO;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserCommand;
import com.cuemymusic.user.service.domain.dto.user.adminstration.create.CreateUserResponse;
import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.entity.builder.UserBuilder;
import com.cuemymusic.user.service.domain.valueobject.Role;
import com.cuemymusic.user.service.domain.valueobject.Subscription;
import com.cuemymusic.user.service.domain.valueobject.UserId;
import com.cuemymusic.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UserCRUDMapper {
    public User createUserCommandToUser(UUID id,CreateUserCommand createUserCommand){
        UserId userId = new UserId(id);
        return new UserBuilder()
                .setId(userId)
                .setFirstName(createUserCommand.firstName())
                .setLastName(createUserCommand.lastName())
                .setEmail(createUserCommand.email())
                .setSubscription(Subscription.Expired)
                .setRole(Role.USER)
                .setEnabled(true)
                .setTokens(List.of())
                .createUser();
    }

    public CreateUserResponse userToCreateUserResponse(User user) {
        UserId userId = user.getId();
        return new CreateUserResponse(userId.getValue(), ZonedDateTime.now());
    }
    public User updateUserCommandToUser(UpdateUserCommand updateUserCommand){
        log.info(updateUserCommand.toString());
        return new UserBuilder()
                .setId(new UserId(updateUserCommand.id()))
                .setFirstName(updateUserCommand.firstName())
                .setLastName(updateUserCommand.lastName())
                .setSubscription(updateUserCommand.subscription())
                .setRole(updateUserCommand.role())
                .createUser();
    }

    public UpdateUserResponse userToUpdateUserResponse(User user) {
       return new UpdateUserResponse(
               userToUserDTO(user),
               user.getDevices().stream().map(d ->
                       new DeviceDTO(
                               d.getDevice().getDeviceId().getValue(),
                               d.getDevice().getName(),
                               d.getRank()
                       )
               ).toList()
       );
    }

    public DeleteUserResponse booleanToDeleteUserResponse(Boolean success){
        return new DeleteUserResponse(success);
    }




    public UserDTO userToUserDTO(User user){
        return new UserDTO(user.getId().getValue(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                "********",
                user.getSubscription(),
                user.getRole(),
                user.getDeviceId().getValue()
        );
    }
}
