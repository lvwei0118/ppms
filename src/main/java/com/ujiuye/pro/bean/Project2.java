package com.ujiuye.pro.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: lvwei
 * @Date: 2019/4/7 11:05
 * @project: ppms
 * @Description: 联表查询实体类2
 */

public class Project2 {
    private Integer pid;

    private String pname;
    /*客户公司*/
    private String coname;
    /*客户公司id*/
    private Integer comname;
    /*客户方负责人*/
    private String comper;
    /*项目负责人*/
    private String ename;
    /*项目负责人id*/
    private Integer empFk;

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


    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

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
        this.pname = pname;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public String getComper() {
        return comper;
    }

    public void setComper(String comper) {
        this.comper = comper;
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
        this.level = level;
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
        this.remark = remark;
    }

    public Integer getComname() {
        return comname;
    }

    public void setComname(Integer comname) {
        this.comname = comname;
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }
}
