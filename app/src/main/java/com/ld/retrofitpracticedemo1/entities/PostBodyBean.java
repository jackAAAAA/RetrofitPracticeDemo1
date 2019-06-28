package com.ld.retrofitpracticedemo1.entities;

/**
 * @Author LD
 * @Time 2019/6/27 18:15
 * @Describe Post请求中的@Body注解
 * @Modify
 */
public class PostBodyBean {

    private String key;
    private int num;
    private boolean isTrue;

    public PostBodyBean(String key, int num, boolean isTrue) {
        this.key = key;
        this.num = num;
        this.isTrue = isTrue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
