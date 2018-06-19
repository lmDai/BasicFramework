package cn.yznu.basicframework.model.bean;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class TestBean {

    private String code;
    private String gmtApply;
    private String gmtBan;
    private String gmtExpire;
    private int id;
    private String mobile;
    private String userType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGmtApply() {
        return gmtApply;
    }

    public void setGmtApply(String gmtApply) {
        this.gmtApply = gmtApply;
    }

    public String getGmtBan() {
        return gmtBan;
    }

    public void setGmtBan(String gmtBan) {
        this.gmtBan = gmtBan;
    }

    public String getGmtExpire() {
        return gmtExpire;
    }

    public void setGmtExpire(String gmtExpire) {
        this.gmtExpire = gmtExpire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
