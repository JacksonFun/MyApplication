package com.example.administrator.myapplication;

/**
 * Created by sunjie on 2018/5/13.
 */

public class Data {
    private int imgId;
    private String content;
    private  int aId;
    private  int bId;

    public Data() {}

    public Data(int imgId, String content) {
        this.imgId = imgId;
        this.content = content;
    }

    public Data(int imgId, String content, int aId) {
        this.imgId = imgId;
        this.content = content;
        this.aId=aId;
    }

    public Data(int imgId, String content, int aId,int bId) {
        this.imgId = imgId;
        this.content = content;
        this.aId=aId;
        this.bId=bId;
    }

    //得到数值
    public int getImgId() {
        return imgId;
    }

    public String getContent() {
        return content;
    }

    public int getaId() {
        return aId;
    }

    public int getbId() {
        return bId;
    }

    //设值
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}