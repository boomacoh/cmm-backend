package com.cuemymusic.club.service.domain.entity.builder;

import com.cuemymusic.club.service.domain.entity.Club;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.User;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.util.List;

public class ClubBuilder {
    private ClubId clubId;
    private UserId adminId;
    private String name;
    private String streetAddress;
    private String cityName;
    private String zipCode;
    private String country;
    private String email;
    private String phoneNumber;
    private List<Device> devices;
    private List<User> disabledUsers;
    private Boolean announce;
    private Integer maxCoachPumps;
    private Integer maxUserSongs;
    private Integer maxCoachSongs;

    public ClubBuilder setClubId(ClubId clubId) {
        this.clubId = clubId;
        return this;
    }

    public ClubBuilder setAdminId(UserId adminId) {
        this.adminId = adminId;
        return this;
    }

    public ClubBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClubBuilder setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public ClubBuilder setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public ClubBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public ClubBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public ClubBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClubBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClubBuilder setDevices(List<Device> devices) {
        this.devices = devices;
        return this;
    }

    public ClubBuilder setDisabledUsers(List<User> disabledUsers) {
        this.disabledUsers = disabledUsers;
        return this;
    }

    public ClubBuilder setAnnounce(Boolean announce) {
        this.announce = announce;
        return this;
    }

    public ClubBuilder setMaxCoachPumps(Integer maxCoachPumps) {
        this.maxCoachPumps = maxCoachPumps;
        return this;
    }

    public ClubBuilder setMaxUserSongs(Integer maxUserSongs) {
        this.maxUserSongs = maxUserSongs;
        return this;
    }

    public ClubBuilder setMaxCoachSongs(Integer maxCoachSongs) {
        this.maxCoachSongs = maxCoachSongs;
        return this;
    }

    public Club createClub() {
        return new Club(clubId, adminId, name, streetAddress, cityName, zipCode, country, email, phoneNumber, devices, disabledUsers, announce, maxCoachPumps, maxUserSongs, maxCoachSongs);
    }
}