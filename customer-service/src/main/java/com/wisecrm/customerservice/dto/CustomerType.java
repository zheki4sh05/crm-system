package com.wisecrm.customerservice.dto;

public enum CustomerType {

    B2C (1),
    B2B (2),
    B2G (3);

    private Integer id;

    CustomerType(Integer id) {
        this.id = id;
    }


    public Integer get() {
        return id;
    }

    @Override
    public String toString() {
        return "CustomerType{" +
                "id=" + id +
                '}';
    }
}
