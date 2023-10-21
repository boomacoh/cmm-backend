package com.cuemymusic.firmware.service.dataaccess.firmware.adapter;

import com.cuemymusic.firmware.service.dataaccess.firmware.entity.FirmwareEntity;
import com.cuemymusic.firmware.service.dataaccess.firmware.mapper.FirmwareDataAccessMapper;
import com.cuemymusic.firmware.service.dataaccess.firmware.repository.FirmwareRepositoryJpa;
import com.cuemymusic.firmware.service.domain.entity.Firmware;
import com.cuemymusic.firmware.service.domain.ports.output.repository.firmware.FirmwareRepository;
import com.cuemymusic.user.service.domain.valueobject.FirmwareId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FirmwareRepositoryImpl implements FirmwareRepository {
    private final FirmwareRepositoryJpa firmwareRepositoryJpa;
    private final FirmwareDataAccessMapper mapper;

    public FirmwareRepositoryImpl(FirmwareRepositoryJpa firmwareRepositoryJpa, FirmwareDataAccessMapper mapper) {
        this.firmwareRepositoryJpa = firmwareRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public List<Firmware> findAll() {
        return firmwareRepositoryJpa.findAll().stream().map(mapper::firmwareEntityFirmware).toList();
    }

    @Override
    public Firmware save(Firmware firmware) {
        return mapper.firmwareEntityFirmware(
                firmwareRepositoryJpa.save(
                        mapper.firmwareToFirmwareEntity(firmware)
                )
        );
    }

    @Override
    public void deleteById(FirmwareId id) {
        firmwareRepositoryJpa.deleteById(id.getValue());
    }

    @Override
    public Firmware findById(FirmwareId id) {
        return mapper.firmwareEntityFirmware(firmwareRepositoryJpa.findById(id.getValue()).get());
    }

    @Override
    public boolean isPresent(FirmwareId id) {
        return firmwareRepositoryJpa.findById(id.getValue()).isPresent();
    }

    @Override
    public Optional<Firmware> getCurrent() {
        List<FirmwareEntity> result =  firmwareRepositoryJpa.findFirmwaresByEnabled(true);
        if(result.size()>0){
            return Optional.of(mapper.firmwareEntityFirmware(result.get(0)));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public void promoteById(FirmwareId id) {
        firmwareRepositoryJpa.promoteById(id.getValue());
    }
}
