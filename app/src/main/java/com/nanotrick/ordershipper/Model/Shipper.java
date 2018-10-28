package com.nanotrick.ordershipper.Model;

/**
 * Created by milymozz on 2018. 4. 8..
 */

public class Shipper {
    private String name, phone, password;

    public Shipper() {
    }

    public Shipper(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
