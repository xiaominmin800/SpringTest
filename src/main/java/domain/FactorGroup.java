package domain;

import lombok.Data;

import java.util.Date;

/**
 * created by chenminqing
 */
@Data
public class FactorGroup {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    private Integer id;

    private String orgCode;

    private String nameEn;

    private String name;

    private String descri;

    private Integer status;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date lastModifyTime;


    public FactorGroup(Integer id, String orgCode, String nameEn, String name, String descri, Integer status, String creator, Date createTime, String modifier, Date lastModifyTime) {
        this.id = id;
        this.orgCode = orgCode;
        this.nameEn = nameEn;
        this.name = name;
        this.descri = descri;
        this.status = status;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.lastModifyTime = lastModifyTime;
    }

    public FactorGroup(Integer id, String nameEn, String name, String descri) {
        this.id = id;
        this.nameEn = nameEn;
        this.name = name;
        this.descri = descri;
    }

    public FactorGroup() {
    }
}
