package com.tomster.mybatis.po;

/**
 * @author meihewang
 * @date 2019/11/25  23:33
 */
public class OrderExt extends Order{

    private String username;

    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
