package com.lzh.storm.action.bean.http;

/**
 * Created by lzh on 2017/11/28.
 */

public class IterationInfo {

    private long modifiedTime;
    private String version;
    private String url;
    private boolean status;
    private String name;

    public IterationInfo(){

    }

    public IterationInfo(long modifiedTime, String name, String version, String url, boolean status){
        this.modifiedTime = modifiedTime;
        this.version = version;
        this.url = url;
        this.status = status;
        this.name = name;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
