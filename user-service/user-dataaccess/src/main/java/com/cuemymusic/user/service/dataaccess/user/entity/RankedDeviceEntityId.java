package com.cuemymusic.user.service.dataaccess.user.entity;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RankedDeviceEntityId implements Serializable {
    private UUID deviceId;
    private UUID userId;
}