package com.major.backend.models;

public class Transfer {

    private String fromMobileNo;
    private String toMobileNo;
    private Double lasttransactionamount;

    public String getFromMobileNo() {
        return fromMobileNo;
    }

    public void setFromMobileNo(String fromMobileNo) {
        this.fromMobileNo = fromMobileNo;
    }

    public String getToMobileNo() {
        return toMobileNo;
    }

    public void setToMobileNo(String toMobileNo) {
        this.toMobileNo = toMobileNo;
    }

    public Double getLasttransactionamount() {
        return lasttransactionamount;
    }

    public void setLasttransactionamount(Double lasttransactionamount) {
        this.lasttransactionamount = lasttransactionamount;
    }

    public Transfer(String fromMobileNo, String toMobileNo, Double amount) {
        this.fromMobileNo = fromMobileNo;
        this.toMobileNo = toMobileNo;
        this.lasttransactionamount = amount;
    }
}
