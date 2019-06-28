package com.ld.retrofitpracticedemo1.entities;

/**
 * @Author LD
 * @Time 2019/6/27 11:56
 * @Describe 文章信息
 * @Modify
 */
public class ArticalInfo {

    private String articalName;
    private String url;

    public String getArticalName() {
        return articalName;
    }

    public void setArticalName(String articalName) {
        this.articalName = articalName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ArticalInfo{" +
                "articalName='" + articalName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
