package com.cuemymusic.club.service.domain.entity;

import com.cuemymusic.user.service.domain.entity.BaseEntity;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import com.cuemymusic.user.service.domain.valueobject.UserId;

import java.util.ArrayList;
import java.util.List;

public class Club extends BaseEntity<ClubId> {
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
    public Club(ClubId clubId,
                UserId adminId,
                String name,
                String streetAddress,
                String cityName,
                String zipCode,
                String country,
                String email,
                String phoneNumber,
                List<Device> devices,
                List<User> disabledUsers,
                Boolean announce,
                Integer maxCoachPumps,
                Integer maxUserSongs,
                Integer maxCoachSongs) {
        this.clubId = clubId;
        this.adminId = adminId;
        this.name = name;
        this.streetAddress = streetAddress;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.devices = devices;
        this.disabledUsers = disabledUsers;
        this.announce = announce;
        this.maxCoachPumps = maxCoachPumps;
        this.maxUserSongs = maxUserSongs;
        this.maxCoachSongs = maxCoachSongs;
    }

    public ClubId getClubId() {
        return clubId;
    }

    public void setClubId(ClubId clubId) {
        this.clubId = clubId;
    }

    public UserId getAdminId() {
        return adminId;
    }

    public void setAdminId(UserId adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<User> getDisabledUsers() {
        return disabledUsers;
    }

    public void setDisabledUsers(List<User> disabledUsers) {
        this.disabledUsers = disabledUsers;
    }
    public void addDisabled(User user){
        List<User> users = new ArrayList(disabledUsers);
        users.add(user);
        setDisabledUsers(users);
    }
    public void removeDisabled(User user){
        List<User> users = new ArrayList(disabledUsers);
        users.removeIf(e-> e.getUserId().getValue() == user.getUserId().getValue());
        setDisabledUsers(users);
    }

    public Boolean getAnnounce() {
        return announce;
    }

    public void setAnnounce(Boolean announce) {
        this.announce = announce;
    }

    public Integer getMaxCoachPumps() {
        return maxCoachPumps;
    }

    public void setMaxCoachPumps(Integer maxCoachPumps) {
        this.maxCoachPumps = maxCoachPumps;
    }

    public Integer getMaxCoachSongs() {
        return maxCoachSongs;
    }

    public void setMaxCoachSongs(Integer maxCoachSongs) {
        this.maxCoachSongs = maxCoachSongs;
    }

    public Integer getMaxUserSongs() {
        return maxUserSongs;
    }

    public void setMaxUserSongs(Integer maxUserSongs) {
        this.maxUserSongs = maxUserSongs;
    }
}
