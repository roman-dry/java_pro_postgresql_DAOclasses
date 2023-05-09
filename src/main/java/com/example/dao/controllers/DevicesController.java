package com.example.dao.controllers;

import com.example.dao.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.dao.models.Device;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/devices")
public class DevicesController {
    private final DeviceService deviceservice;

    @Autowired
    public DevicesController(DeviceService deviceservice) {
        this.deviceservice = deviceservice;
    }

    @GetMapping()
    public String readAllDevices(Model model) {
        model.addAttribute("devices", deviceservice.readDevices());
        return "index1";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("device", deviceservice.readDeviceById(id));
        return "show1";
    }

    @GetMapping("/new")
    public String newDevice(Model model) {
        model.addAttribute("device", new Device());
        return "new1";
    }

    @PostMapping()
    public String create(@ModelAttribute("device") Device device) {
        deviceservice.createDevice(device);
        return "redirect:/devices";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("device", deviceservice.readDeviceById(id));
        return "edit1";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("device") Device device, @PathVariable("id") int id) {
        deviceservice.updateDeviceById(device, id);
        return "redirect:/devices";
    }
    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") int id) {
        deviceservice.deleteDeviceById(id);
        return "redirect:/devices";
    }
}
