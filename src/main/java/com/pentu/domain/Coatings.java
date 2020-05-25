package com.pentu.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Coatings {
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 电流
     */
    private Double current;

    /**
     * 总气流量
     */
    private Double totalFlowAmount;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    public WorkingGasType getWorkingGasType() {
        return workingGasType;
    }

    public void setWorkingGasType(WorkingGasType workingGasType) {
        this.workingGasType = workingGasType;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    public CoatingDevice getCoatingDevice() {
        return coatingDevice;
    }

    public void setCoatingDevice(CoatingDevice coatingDevice) {
        this.coatingDevice = coatingDevice;
    }

    /**
     * 工作气体
     */
    private WorkingGasType workingGasType;

    /**
     * 设备
     */
    private CoatingDevice coatingDevice;

    /**
     * 喷涂距离
     */
    private Double distance;

    /**
     * 送粉率
     */
    private Double powderRate;

    /**
     * 特性
     */
    private String features;

    /**
     * 文献
     */
    private String docReferences;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
