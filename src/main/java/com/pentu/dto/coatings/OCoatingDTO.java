package com.pentu.dto.coatings;

public class OCoatingDTO extends ICoatingDTO {
    private Integer id;
    private String workingGasName;
    private String coatingDeviceName;

    public String getWorkingGasName() {
        return workingGasName;
    }

    public void setWorkingGasName(String workingGasName) {
        this.workingGasName = workingGasName;
    }

    public String getCoatingDeviceName() {
        return coatingDeviceName;
    }

    public void setCoatingDeviceName(String coatingDeviceName) {
        this.coatingDeviceName = coatingDeviceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
