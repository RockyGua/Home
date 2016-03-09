package com.rocky.validation;


import com.rocky.validation.combinationConstraints.NotEmpty2;
import com.rocky.validation.multipleConstraints.PatternOfString;
import com.rocky.validation.singleConstraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee {
    @NotNull(message = "The id of employee can not be null")
    private Integer id;

    @NotNull(message = "The name of employee can not be null")
    @Size(min = 1, max = 10, message = "The size of employee's name must between 1 and 10")
    private String name;

    //
    @NotEmpty
    private String company;

    @PatternOfString.List({
            @PatternOfString(mustContainLetter = "CH",
                    message = "It does not belong to China"),
            @PatternOfString(mustContainLetter="MainLand",
                    message="It does not belong to MainLand")})
    private String place;

    @NotEmpty2
    private String hobby;

    @Valid
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
