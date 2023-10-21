package com.cuemymusic.club.service.domain.club.mapper.device;

import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.createDevice.AddDeviceResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceDTO;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.DeviceListResponse;
import com.cuemymusic.club.service.domain.club.dto.device.queryDevices.UserDeviceDTO;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceCommand;
import com.cuemymusic.club.service.domain.club.dto.device.updateDevice.UpdateDeviceResponse;
import com.cuemymusic.club.service.domain.entity.Device;
import com.cuemymusic.club.service.domain.entity.builder.DeviceBuilder;
import com.cuemymusic.user.service.domain.valueobject.ClubId;
import com.cuemymusic.user.service.domain.valueobject.DeviceId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class DeviceDataMapper {

    public DeviceDTO deviceToDeviceDTO(Device device){
        return  DeviceDTO.builder()
                .id(device.getId().getValue())
                .name(device.getName())
                .dns(device.getDns())
                .connectionInterface(device.getConnectionInterface())
                .enabled(device.getEnabled())
                .gateway(device.getGateway())
                .ipAddress(device.getIpAddress())
                .isDhcp(device.getDhcp())
                .subnet(device.getSubnet())
                .wifiPassword(device.getWifiPassword())
                .wifiSsid(device.getWifiSsid())
                .zonedDateTime(device.getZonedDateTime())
                .clubName(device.getClub().getName())
                .clubId(device.getClub().getClubId().getValue())
                .build();
    }
    public UserDeviceDTO deviceToUserDeviceDTO(Device device){
        return  UserDeviceDTO.builder()
                .id(device.getId().getValue())
                .name(device.getName())
                .build();
    }
    public Device deviceDtoToDevice(DeviceDTO deviceDTO){
        return new DeviceBuilder()
                .setId(new DeviceId(deviceDTO.id()))
                .setName(deviceDTO.name())
                .setDns(deviceDTO.dns())
                .setConnectionInterface(deviceDTO.connectionInterface())
                .setEnabled(deviceDTO.enabled())
                .setGateway(deviceDTO.gateway())
                .setIpAddress(deviceDTO.ipAddress())
                .setIsDhcp(deviceDTO.isDhcp())
                .setSubnet(deviceDTO.subnet())
                .setWifiPassword(deviceDTO.wifiPassword())
                .setWifiSsid(deviceDTO.wifiSsid())
                .setZonedDateTime(deviceDTO.zonedDateTime())
                .createDevice();
    }

       public Device addDeviceCommandToDevice(AddDeviceCommand addDeviceCommand){
        return new DeviceBuilder()
                .setId(new DeviceId(UUID.randomUUID()))
                .setName(addDeviceCommand.name())
                .setDns(addDeviceCommand.dns())
                .setConnectionInterface(addDeviceCommand.connectionInterface())
                .setEnabled(addDeviceCommand.enabled())
                .setGateway(addDeviceCommand.gateway())
                .setIpAddress(addDeviceCommand.ipAddress())
                .setIsDhcp(addDeviceCommand.isDhcp())
                .setSubnet(addDeviceCommand.subnet())
                .setWifiPassword(addDeviceCommand.wifiPassword())
                .setWifiSsid(addDeviceCommand.wifiSsid())
                .setZonedDateTime(addDeviceCommand.zonedDateTime())
                .createDevice();
    }
    public AddDeviceResponse deviceToAddDeviceResponse(Device device){
        return AddDeviceResponse.builder()
                .deviceDTO(deviceToDeviceDTO(device))
                .build();
    }
    public DeviceListResponse deviceListToDeviceListResponse(List<Device> devices){
        return DeviceListResponse.builder()
                .devices(
                        devices.stream()
                                .map(this::deviceToDeviceDTO)
                                .toList()
                )
                .build();
    }
    public Device updateDeviceCommandToDevice(UpdateDeviceCommand updateDeviceCommand){
        return new DeviceBuilder()
                .setId(new DeviceId(updateDeviceCommand.id()))
                .setName(updateDeviceCommand.name())
                .setDns(updateDeviceCommand.dns())
                .setConnectionInterface(updateDeviceCommand.connectionInterface())
                .setEnabled(updateDeviceCommand.enabled())
                .setGateway(updateDeviceCommand.gateway())
                .setIpAddress(updateDeviceCommand.ipAddress())
                .setIsDhcp(updateDeviceCommand.isDhcp())
                .setSubnet(updateDeviceCommand.subnet())
                .setWifiPassword(updateDeviceCommand.wifiPassword())
                .setWifiSsid(updateDeviceCommand.wifiSsid())
                .setZonedDateTime(updateDeviceCommand.zonedDateTime())
                .createDevice();
    }
    public UpdateDeviceResponse deviceToUpdateDeviceResponse(Device device){
        return UpdateDeviceResponse.builder()
                .deviceDTO(deviceToDeviceDTO(device))
                .build();
    }


}
