package com.cuemymusic.user.service.domain.mapper.users;

import com.cuemymusic.user.service.domain.dto.user.adminstration.find.all.FindUsersResponse;
import com.cuemymusic.user.service.domain.dto.user.common.UserDTO;
import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleCommand;
import com.cuemymusic.user.service.domain.dto.user.management.update.roles.ChangeRoleResponse;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import lombok.extern.slf4j.Slf4j;
import com.cuemymusic.user.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.Role;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@Slf4j
public class UserManagementMapper {

    private final UserCRUDMapper userCRUDMapper;

    public UserManagementMapper(UserCRUDMapper userCRUDMapper) {
        this.userCRUDMapper = userCRUDMapper;
    }

    public Role changeRoleCommandToUserRole(ChangeRoleCommand changeRoleCommand){
        return changeRoleCommand.isCoach() ? Role.COACH : Role.USER;
    }



    public ChangeRoleResponse userToChangeRoleResponse(User user) {
        return new ChangeRoleResponse(user.getId().getValue(),user.getRole(), ZonedDateTime.now());
    }

    public FindUsersResponse usersToFindUsersResponse(List<User> users){
        List<UserDTO> userDTOs = users.stream().map(userCRUDMapper::userToUserDTO).toList();
        return new FindUsersResponse(userDTOs);
    }



}
