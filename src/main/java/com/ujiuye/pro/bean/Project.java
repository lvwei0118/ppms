package com.ujiuye.pro.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.emp.bean.Employee;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Project {
    private Integer pid;

    private String pname;
    /*客户公司*/
    private Integer comname;
    /*客户方负责人*/
    private String comper;

    private Integer empFk1;

    private Integer empcount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date starttime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buildtime;

    private Integer cost;

    private String level;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endtime;

    private String remark;

    private Integer empFk;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getComname() {
        return comname;
    }

    public void setComname(Integer comname) {
        this.comname = comname;
    }

    public String getComper() {
        return comper;
    }

    public void setComper(String comper) {
        this.comper = comper == null ? null : comper.trim();
    }

    public Integer getEmpFk1() {
        return empFk1;
    }

    public void setEmpFk1(Integer empFk1) {
        this.empFk1 = empFk1;
    }

    public Integer getEmpcount() {
        return empcount;
    }

    public void setEmpcount(Integer empcount) {
        this.empcount = empcount;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Date buildtime) {
        this.buildtime = buildtime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }
}