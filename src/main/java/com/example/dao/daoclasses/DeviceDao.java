package com.example.dao.daoclasses;

import com.example.dao.MyConnection;
import com.example.dao.models.Device;

import java.sql.*;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceDao {
    public void createDevice(Device device) {

        try(Connection cn = MyConnection.createNewConnection();
            Statement sm = cn.createStatement()) {
            sm.execute("insert into devices values(DEFAULT, " +
                    "'" + device.getName() + "', '" + device.getMac_address() + "', " +
                    "'" + device.getUser_id() + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Device> readDevices() {
        List allDevices = new ArrayList<Device>();
        try(Connection cn = MyConnection.createNewConnection();
            Statement sm = cn.createStatement()) {
            ResultSet rs = sm.executeQuery("select * from devices");

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mac_address = rs.getString("mac_address");
                String user_id = rs.getString("user_id");
                allDevices.add(new Device(id, name, mac_address, user_id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allDevices;

    }

    public Device readDeviceById(int id) {
        Device device = new Device();
        try(Connection cn = MyConnection.createNewConnection();
            Statement sm = cn.createStatement()) {
            ResultSet rs = sm.executeQuery("select * from devices where id = '" + id +"'");

            while(rs.next()) {
                device.setId(id);
                device.setName(rs.getString("name"));
                device.setMac_address(rs.getString("mac_address"));
                device.setUser_id(rs.getString("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return device;

    }

    public void updateDeviceById(Device device, int id) {
        try(Connection cn = MyConnection.createNewConnection()) {
            PreparedStatement ps = cn.prepareStatement("update devices " +
                    "set name = '"+ device.getName() + "', " +
                    "mac_address = '"+ device.getMac_address() + "', " +
                    "user_id = '"+ device.getUser_id() + "' where id = '" + id +"'");
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeviceById(int id) {
        try(Connection cn = MyConnection.createNewConnection()) {
            PreparedStatement ps = cn.prepareStatement("delete from devices " +
                    "where id = '" + id +"'");
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

