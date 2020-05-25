package com.pentu.dto.coatings;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ICoatingDTO {
    private String name;
    private Double current;
    private Double totalFlowAmount;
    private Integer workingGasId;
    private Integer coatingDeviceId;
    private Double distance;
    private Double powderRate;
    private String features;
    private String docReferences;

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getTotalFlowAmount() {
        return totalFlowAmount;
    }

    public void setTotalFlowAmount(Double totalFlowAmount) {
        this.totalFlowAmount = totalFlowAmount;
    }

    @NotNull
    public Integer getWorkingGasId() {
        return workingGasId;
    }

    public void setWorkingGasId(Integer workingGasId) {
        this.workingGasId = workingGasId;
    }

    @NotNull
    public Integer getCoatingDeviceId() {
        return coatingDeviceId;
    }

    public void setCoatingDeviceId(Integer coatingDeviceId) {
        this.coatingDeviceId = coatingDeviceId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getPowderRate() {
        return powderRate;
    }

    public void setPowderRate(Double powderRate) {
        this.powderRate = powderRate;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDocReferences() {
        return docReferences;
    }

    public void setDocReferences(String docReferences) {
        this.docReferences = docReferences;
    }
}
