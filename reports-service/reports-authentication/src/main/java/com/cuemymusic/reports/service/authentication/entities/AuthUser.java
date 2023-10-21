package com.cuemymusic.reports.service.authentication.entities;

import com.cuemymusic.user.service.domain.valueobject.Subscription;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthUser implements UserDetails {


    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean enabled;
    private Role role;
    private Subscription subscription;

    private List<AuthToken> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        log.error("Checking for non expired for user : "+email);
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.error("Checking for non locked for user : "+email);
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.error("Checking for creds non expired for user : "+email);
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(id, authUser.id) && Objects.equals(firstname, authUser.firstname) && Objects.equals(lastname, authUser.lastname) && Objects.equals(email, authUser.email) && Objects.equals(password, authUser.password) && Objects.equals(enabled, authUser.enabled) && role == authUser.role && subscription == authUser.subscription && Objects.equals(tokens, authUser.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}