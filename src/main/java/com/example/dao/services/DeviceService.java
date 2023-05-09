package com.example.dao.services;

import com.example.dao.daoclasses.DeviceDao;
import com.example.dao.models.Device;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class DeviceService {
    private final DeviceDao devicedao;

    public void createDevice(Device device) {
        devicedao.createDevice(device);
    }
    public List<Device> readDevices() {
        return devicedao.readDevices();
    }
    public Device readDeviceById(int id) {
        return devicedao.readDeviceById(id);
    }
    public void updateDeviceById(Device device, int id) {
        devicedao.updateDeviceById(device, id);
    }

    public void deleteDeviceById(int id) {
        devicedao.deleteDeviceById(id);
    }


}
