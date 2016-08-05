package com.joany.wechat.bean;

/**
 * Created by joany on 2016/8/1.
 */
public class UserInfo {
    private String userName;
    private String password;
    private String headUrl;//头像保存路径
    private int headDrawableId;
    private String signature;//个性签名
    private String sex;//性别：M男，W女
    private String location;
    private String birthday;
    private String telephone;
    private String type;//类型：N普通用户，P公众号

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getHeadUrl(){
        return headUrl;
    }

    public void setHeadDrawableId(int headDrawableId) {
        this.headDrawableId = headDrawableId;
    }

    public int getHeadDrawableId(){
        return headDrawableId;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature(){
        return signature;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex(){
        return sex;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation(){
        return location;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
