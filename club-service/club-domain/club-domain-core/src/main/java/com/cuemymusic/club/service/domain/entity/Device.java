package com.cuemymusic.club.service.domain.entity;

import com.cuemymusic.club.service.domain.entity.builder.PlaylistSongBuilder;
import com.cuemymusic.user.service.domain.entity.BaseEntity;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import com.cuemymusic.user.service.domain.valueobject.PlaylistSongId;
import com.cuemymusic.user.service.domain.valueobject.SongId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Device extends BaseEntity<DeviceId> {
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

    public Device(DeviceId id,
                  String name,
                  ZonedDateTime zonedDateTime,
                  String wifiSsid,
                  String connectionInterface,
                  String wifiPassword,
                  String ipAddress,
                  Boolean isDhcp,
                  String subnet,
                  String gateway,
                  String dns,
                  Club club,
                  Boolean enabled,
                  List<PlaylistSong> playlist) {
        this.id = id;
        this.name = name;
        this.zonedDateTime = zonedDateTime;
        this.wifiSsid = wifiSsid;
        this.connectionInterface = connectionInterface;
        this.wifiPassword = wifiPassword;
        this.ipAddress = ipAddress;
        this.isDhcp = isDhcp;
        this.subnet = subnet;
        this.gateway = gateway;
        this.dns = dns;
        this.club = club;
        this.enabled = enabled;
        this.playlist = playlist;
    }

    @Override
    public DeviceId getId() {
        return id;
    }

    @Override
    public void setId(DeviceId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public String getWifiSsid() {
        return wifiSsid;
    }

    public void setWifiSsid(String wifiSsid) {
        this.wifiSsid = wifiSsid;
    }

    public String getConnectionInterface() {
        return connectionInterface;
    }

    public void setConnectionInterface(String connectionInterface) {
        this.connectionInterface = connectionInterface;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getDhcp() {
        return isDhcp;
    }

    public void setDhcp(Boolean dhcp) {
        isDhcp = dhcp;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<PlaylistSong> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlaylistSong> playlist) {
        this.playlist = playlist;
    }

    public void addSong(PlaylistSong song){
        List<PlaylistSong> modifiableList = new ArrayList(playlist);
        modifiableList.add(song);
        this.playlist = modifiableList;
    }
    public PlaylistSong getSong(PlaylistSongId songId){
        int i =  playlist.indexOf(
                new PlaylistSongBuilder()
                        .setId(songId)
                        .setOrder(10000.0)
                        .createPlaylistSong()
        );
        if(i < 0) return null;
        return playlist.get(i);
    }
    public void removeSong(PlaylistSong song){
        List<PlaylistSong> modifiableList = new ArrayList(playlist);
        modifiableList.remove(song);
        this.playlist = modifiableList;
    }
}
