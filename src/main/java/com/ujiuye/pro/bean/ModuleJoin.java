package com.ujiuye.pro.bean;

/**
 * @Auther: lvwei
 * @Date: 2019/4/7 19:16
 * @project: ppms
 * @Description: 联表查询实体类（连需求表）
 */
public class ModuleJoin {

    private Integer id;

    private String proname;

    private Integer analysisFk;

    private String title;

    private String modname;

    private String level;

    private String simpledis;

    private String detaileddis;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSimpledis() {
        return simpledis;
    }

    public void setSimpledis(String simpledis) {
        this.simpledis = simpledis;
    }

    public String getDetaileddis() {
        return detaileddis;
    }

    public void setDetaileddis(String detaileddis) {
        this.detaileddis = detaileddis;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAnalysisFk() {
        return analysisFk;
    }

    public void setAnalysisFk(Integer analysisFk) {
        this.analysisFk = analysisFk;
    }
}
