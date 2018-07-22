package com.example.aravindraj.vaterkanes.model;

import java.io.Serializable;

public class NewCustomerListData implements Serializable {

    private Integer customer_id;
    private String customer_name;
    private String customer_phoneno;
    private String customer_address;
    private String customer_img;

    public NewCustomerListData(Integer customer_id, String customer_name,
                               String customer_phoneno, String customer_address) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_phoneno = customer_phoneno;
        this.customer_address = customer_address;
        this.customer_img = customer_img;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phoneno() {
        return customer_phoneno;
    }

    public void setCustomer_phoneno(String customer_phoneno) {
        this.customer_phoneno = customer_phoneno;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_img() {
        return customer_img;
    }

    public void setCustomer_img(String customer_img) {
        this.customer_img = customer_img;
    }
}
