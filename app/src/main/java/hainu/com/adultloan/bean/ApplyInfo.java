package hainu.com.adultloan.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/8/12.
 */
public class ApplyInfo extends BmobObject {
    private String applyId;
    private String name;
    private String qq;
    private String certificate;
    private String province;
    private String weixin;
    private String money;
    private String mouths;
    private String phone;
    private String applyType;

    public ApplyInfo(String applyId, String name, String qq, String certificate, String province, String weixin, String money, String mouths, String phone) {
        this.applyId = applyId;
        this.name = name;
        this.qq = qq;
        this.certificate = certificate;
        this.province = province;
        this.weixin = weixin;
        this.money = money;
        this.mouths = mouths;
        this.phone = phone;
    }

    public ApplyInfo(String applyId, String name, String qq, String certificate, String province, String weixin, String money, String mouths, String phone, String applyType) {
        this.applyId = applyId;
        this.name = name;
        this.qq = qq;
        this.certificate = certificate;
        this.province = province;
        this.weixin = weixin;
        this.money = money;
        this.mouths = mouths;
        this.phone = phone;
        this.applyType = applyType;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMouths() {
        return mouths;
    }

    public void setMouths(String mouths) {
        this.mouths = mouths;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
}
