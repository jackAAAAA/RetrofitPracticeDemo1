package com.ld.retrofitpracticedemo1.entities;

/**
 * @Author LD
 * @Time 2019/6/26 18:17
 * @Describe 用户信息
 * @Modify
 */
public class UserInfo {

    //用户ID
    private String userId;
    //用户名
    private String userName;
    //用户描述
    private String describe;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
