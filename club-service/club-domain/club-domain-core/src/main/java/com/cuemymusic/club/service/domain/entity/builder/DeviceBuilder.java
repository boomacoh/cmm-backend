package com.cuemymusic.club.service.domain.entity.builder;

import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.PlaylistSong;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;

import java.time.ZonedDateTime;
import java.util.List;

public class DeviceBuilder {
    private DeviceId id;
    private String name;
    private ZonedDateTime zonedDateTime;
    private String wifiSsid;
    private String connectionInterface;
    private String wifiPassword;
    private String ipAddress;
    private Boolean isDhcp;
    private String subnet;
    private String gateway;
    private String dns;
    private Club club;
    private Boolean enabled;
    private List<PlaylistSong> playlist;

    public DeviceBuilder setId(DeviceId id) {
        this.id = id;
        return this;
    }

    public DeviceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DeviceBuilder setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
        return this;
    }

    public DeviceBuilder setWifiSsid(String wifiSsid) {
        this.wifiSsid = wifiSsid;
        return this;
    }

    public DeviceBuilder setConnectionInterface(String connectionInterface) {
        this.connectionInterface = connectionInterface;
        return this;
    }

    public DeviceBuilder setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
        return this;
    }

    public DeviceBuilder setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public DeviceBuilder setIsDhcp(Boolean isDhcp) {
        this.isDhcp = isDhcp;
        return this;
    }

    public DeviceBuilder setSubnet(String subnet) {
        this.subnet = subnet;
        return this;
    }

    public DeviceBuilder setGateway(String gateway) {
        this.gateway = gateway;
        return this;
    }

    public DeviceBuilder setDns(String dns) {
        this.dns = dns;
        return this;
    }

    public DeviceBuilder setClub(Club club) {
        this.club = club;
        return this;
    }

    public DeviceBuilder setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public DeviceBuilder setPlaylist(List<PlaylistSong> playlist) {
        this.playlist = playlist;
        return this;
    }

    public Device createDevice() {
        return new Device(id, name, zonedDateTime, wifiSsid, connectionInterface, wifiPassword, ipAddress, isDhcp, subnet, gateway, dns, club, enabled, playlist);
    }
}