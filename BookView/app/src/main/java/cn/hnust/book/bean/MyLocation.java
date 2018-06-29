package cn.hnust.book.bean;

/**
 * Created by tjouyang on 2018/4/20.
 *
 * @author tjouyang
 */
// 转成自己的Location， 利用Gson把百度地图Location转Json时，字段被加密了不好分析
public class MyLocation {
    private double latitude;
    private double longitude;
    private String province;
    private String city;
    private String cityCode;
    private String district;
    private String street;
    private String street_number;
    private String floor;
    private String address;
    private int studentId;

    @Override
    public String toString() {
        return "MyLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", street_number='" + street_number + '\'' +
                ", floor='" + floor + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public double getLatituede() {
        return latitude;
    }

    public void setLatituede(double latituede) {
        this.latitude = latituede;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
