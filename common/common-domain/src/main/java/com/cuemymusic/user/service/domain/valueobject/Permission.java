package com.cuemymusic.user.service.domain.valueobject;

public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    COACH_READ("coach:read"),
    USER_READ("user:read"),
    USER_UPLOAD("user:upload"),
    USER_PLAY("user:play"),
    USER_SKIP("user:skip"),
    DEVICE_ACCESS("device:access"),
    ;

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

