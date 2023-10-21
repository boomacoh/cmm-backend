package com.cuemymusic.user.service.dataaccess.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.UUID;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ranked_device")
@ToString
public class RankedDeviceEntity {
    @EmbeddedId
    private RankedDeviceEntityId id;

    @MapsId("deviceId") // Maps the "deviceId" field of the composite key
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private DeviceEntity device;

    @MapsId("userId") // Maps the "deviceId" field of the composite key
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private UserEntity user;

    private Integer rank;
}
