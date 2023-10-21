package com.cuemymusic.firmware.service.dataaccess.firmware.entity;

import com.cuemymusic.firmware.service.dataaccess.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "firmware")
@NoArgsConstructor
@AllArgsConstructor
public class FirmwareEntity {
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity user;
    private Integer version;
    private String name;
    private String location;
    private Boolean enabled;
}
