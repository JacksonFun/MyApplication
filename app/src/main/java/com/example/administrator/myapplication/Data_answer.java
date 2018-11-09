package com.example.administrator.myapplication;

/**
 * Created by sunjie on 2018/5/13.
 */

public class Data_answer {
    private int imgId;
    private String content;

    public Data_answer() {}

    public Data_answer(int imgId, String content) {
        this.imgId = imgId;
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public String getContent() {
        return content;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}