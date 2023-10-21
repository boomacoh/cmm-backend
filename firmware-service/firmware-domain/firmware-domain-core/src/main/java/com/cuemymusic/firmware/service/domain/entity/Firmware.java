package com.cuemymusic.firmware.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.FirmwareId;

public class Firmware {
    private FirmwareId id;
    private User user;
    private Integer version;
    private String name;
    private String location;
    private Boolean enabled;

    public Firmware(FirmwareId id,
                    User user,
                    Integer version,
                    String name,
                    String location,
                    Boolean enabled) {
        this.id = id;
        this.user = user;
        this.version = version;
        this.name = name;
        this.location = location;
        this.enabled = enabled;
    }

    public FirmwareId getId() {
        return id;
    }

    public void setId(FirmwareId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
