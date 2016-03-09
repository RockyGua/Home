package com.rocky.validation;

import com.sun.javafx.beans.annotations.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

public class Address {

    @NotEmpty(message = "province not allowed be empty.")
    private String province;
    @NotEmpty(message = "city not allowed be empty.")
    private String city;

    private String street;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
