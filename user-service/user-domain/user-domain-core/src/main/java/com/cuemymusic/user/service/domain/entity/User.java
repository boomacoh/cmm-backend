package com.cuemymusic.user.service.domain.entity;

import com.cuemymusic.user.service.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;

public class User extends AggregateRoot<UserId> {
    public User(UserId id,
                String firstName,
                String lastName,
                String email,
                String password,
                Subscription subscription,
                Role role,
                ResetPasswordToken resetPasswordToken,
                Boolean enabled,
                List<Song> songs,
                List<Song> favoriteSongs,
                List<Token> tokens,
                List<RankedDevice> devices, DeviceId deviceId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.role = role;
        this.resetPasswordToken = resetPasswordToken;
        this.enabled = enabled;
        this.favoriteSongs = favoriteSongs;
        this.tokens = tokens;
        this.devices = devices;
        this.songs = songs;
        this.deviceId = deviceId;
    }

    private UserId id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Subscription subscription;
    private Role role;
    private ResetPasswordToken resetPasswordToken;
    private Boolean enabled;
    private List<Song> songs;
    private List<Song> favoriteSongs;
    private List<Token> tokens;

    private List<RankedDevice> devices;

    private DeviceId deviceId;
    @Override
    public UserId getId() {
        return id;
    }

    @Override
    public void setId(UserId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ResetPasswordToken getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(ResetPasswordToken resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public void setFavoriteSongs(List<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<RankedDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<RankedDevice> devices) {
        this.devices = devices;
    }


    public void addSong(Song song){
        List modifiableList = new ArrayList(songs);
        modifiableList.add(song);
        this.songs = modifiableList;
    }
    public void removeSong(SongId songId){
        List<Song> modifiableList = new ArrayList(songs);
        modifiableList.removeIf(s -> s.getId().equals(songId));
        this.songs = modifiableList;
    }
    public void setFavorite(Song song){
        List<Song> modifiableList = new ArrayList(favoriteSongs);
        modifiableList.add(song);
        this.favoriteSongs = modifiableList;
    }
    public void clearFavorite(SongId songId){
        List<Song> modifiableList = new ArrayList(favoriteSongs);
        modifiableList.removeIf(s -> s.getId().equals(songId));
        this.favoriteSongs = modifiableList;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }
}
