package com.cuemymusic.club.service.domain.club.dto.club.management;

import java.util.UUID;




public record ManageClubCommand(Boolean announce,
                                Integer maxCoachPumps,
                                Integer maxUserSongs,
                                Integer maxCoachSongs) {
}
