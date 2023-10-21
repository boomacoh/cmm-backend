package com.cuemymusic.user.service.dataaccess.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "devices")
public class DeviceEntity  {
    @Id
    @Column(updatable = false)
    private UUID id;
    @Column(updatable = false)
    private String name;
}
