package cn.hnust.book.bean;

/**
 * Created by tjouyang on 2018/4/20.
 *
 * @author tjouyang
 */

public class DeviceBean {
    private String deviceId;
    private String macAddr;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    @Override
    public String toString() {
        return "DeviceBean{" +
                "deviceId='" + deviceId + '\'' +
                ", macAddr='" + macAddr + '\'' +
                '}';
    }
}
