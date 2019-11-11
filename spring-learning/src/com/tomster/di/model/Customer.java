package com.tomster.di.model;

/**
 * @author meihewang
 * @date 2019/11/03  22:44
 */
public class Customer {

    private String name;
    private String sex;
    private double pi;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", pi=" + pi +
                ", address=" + address +
                '}';
    }
}
