package com.cuemymusic.user.service.domain.valueobject;


import java.util.Set;

import static com.cuemymusic.user.service.domain.valueobject.Permission.*;


public enum Role {

    USER(
            Set.of(
                USER_READ,
                USER_UPLOAD
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    USER_READ,
                    USER_UPLOAD,
                    USER_PLAY,
                    USER_SKIP
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    USER_READ,
                    USER_UPLOAD,
                    USER_PLAY,
                    USER_SKIP
            )
    ),
    COACH(
            Set.of(
                    COACH_READ,
                    USER_READ,
                    USER_UPLOAD,
                    USER_PLAY,
                    USER_SKIP
            )
    ),
    DEVICE(
            Set.of(DEVICE_ACCESS)
    )
    ;

    private final Set<Permission> permissions;


    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}