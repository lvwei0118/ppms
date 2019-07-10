package com.ujiuye.usual.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Comparison {
    private Integer cid;

    private String company;
    /*营业额*/
    private Double targetmoney;
    /*年份*/
    private String year;
    /*业务*/
    private String business;
    /*优势*/
    private String priority;
    /*劣势*/
    private String disadvantage;
    /*行业地位*/
    private String status;
    /*员工数量*/
    private int empCount;
    /*创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;
    /*描述*/
    private String description;
    private Integer empFK;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getTargetmoney() {
        return targetmoney;
    }

    public void setTargetmoney(Double targetmoney) {
        this.targetmoney = targetmoney;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmpCount() {
        return empCount;
    }

    public void setEmpCount(int empCount) {
        this.empCount = empCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEmpFK() {
        return empFK;
    }

    public void setEmpFK(Integer empFK) {
        this.empFK = empFK;
    }
}