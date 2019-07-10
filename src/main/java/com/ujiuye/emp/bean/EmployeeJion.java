package com.ujiuye.emp.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: lvwei
 * @Date: 2019/4/9 16:35
 * @project: ppms
 * @Description: 联表查询（员工-职位）
 */
public class EmployeeJion {
    private Integer eid;

    private String ename;

    private String esex;

    private Integer eage;

    private String telephone;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date hiredate;

    private String pnum;

    private String username;

    private String password;

    private String remark;
    /*职位*/
    private Integer pFk;
    private String position;
    /*部门*/
    private Integer dFk;
    private String dept;
     /*等级*/
    private Integer lFk;
    private String level;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    public Integer getEage() {
        return eage;
    }

    public void setEage(Integer eage) {
        this.eage = eage;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getpFk() {
        return pFk;
    }

    public void setpFk(Integer pFk) {
        this.pFk = pFk;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getdFk() {
        return dFk;
    }

    public void setdFk(Integer dFk) {
        this.dFk = dFk;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getlFk() {
        return lFk;
    }

    public void setlFk(Integer lFk) {
        this.lFk = lFk;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
