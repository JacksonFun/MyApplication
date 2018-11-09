package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class daihuida extends denglu{

    private Button btnshow;
    private int  flag=0;
    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;

    private String detail = "";
    private final static String HTML_URL3 = "http://www.buqi5.cn:8080/web/index3.php";

    // 用于刷新界面
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText( daihuida.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    parseEasyJson(detail);
                    Toast.makeText( daihuida.this, "加载完毕", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        ;
    };
    @Override

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.daihuida);

        mContext = daihuida.this;
        bindViews();
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(mAdapter);

        setViews();
    }

    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_test);
    }

    // 消息传递到服务器
    private void setViews(){
        new Thread() {
            public void run() {
                try {
                    detail = GetData.getHtml(HTML_URL3);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("TAG",e.getMessage());
                    handler.sendEmptyMessage(0x001);
                }
                handler.sendEmptyMessage(0x002);
            };
        }.start();
    }

    // 解析Json字符串:
    private void parseEasyJson(String json){
        ArrayList<Person> persons = new ArrayList<Person>();
        try{
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Person person = new Person();
                person.setId(i+"");
                person.setName(jsonObject.getString("theme"));
                person.setAge(jsonObject.getString("qid"));
                mAdapter.add(new Data(R.mipmap.ic_icon_qitao,jsonObject.getString("theme"),jsonObject.getInt("id") ));
                persons.add(person);
                //Log.e("Json", person.getName());
                //Log.e("Json", person.getAge());

            }
        }catch (Exception e){e.printStackTrace();}
    }



}
