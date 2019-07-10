package com.ujiuye.pro.bean;

/**
 * @Auther: lvwei
 * @Date: 2019/4/8 21:18
 * @project: ppms
 * @Description: 联表查询实体类
 */
public class AttachmentJion {

    private Integer id;
    /*项目id*/
    private Integer proFk;
    /*项目名称*/
    private String pname;
    /*附件个数*/
    private Integer num;
    private String attname;

    private String attdis;

    private String remark;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProFk() {
        return proFk;
    }

    public void setProFk(Integer proFk) {
        this.proFk = proFk;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAttname() {
        return attname;
    }

    public void setAttname(String attname) {
        this.attname = attname;
    }

    public String getAttdis() {
        return attdis;
    }

    public void setAttdis(String attdis) {
        this.attdis = attdis;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
