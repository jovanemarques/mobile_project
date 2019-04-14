package ca.centennialcollege.comp304_miniproject.models;

import java.io.Serializable;

public class Address implements Serializable {
    private int streetNumber;
    private String streetName;
    private String aptSuiteUnit;
    private String city;
    private String province;
    private String postalCode;
    private float latitude;
    private float longitude;

    public Address() {
    }

    public Address(int streetNumber, String streetName, String aptSuiteUnit, String city,
                   String province, String postalCode, float latitude, float longitude) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.aptSuiteUnit = aptSuiteUnit;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAptSuiteUnit() {
        return aptSuiteUnit;
    }

    public void setAptSuiteUnit(String aptSuiteUnit) {
        this.aptSuiteUnit = aptSuiteUnit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(streetNumber);
        sb.append(" ");
        sb.append(streetName);
        sb.append("\n");
        if (!aptSuiteUnit.isEmpty()) {
            sb.append(aptSuiteUnit);
            sb.append("\n");
        }
        sb.append(city);
        sb.append(", ");
        sb.append(province);
        sb.append(" - ");
        sb.append(postalCode);

        return sb.toString();
    }
}
