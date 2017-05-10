package com.example.binomo.models;


import java.io.Serializable;
import java.util.Date;

public class Point implements Serializable {

    private Date date;
    private double rate;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
