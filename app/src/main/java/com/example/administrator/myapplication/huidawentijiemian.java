package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class huidawentijiemian extends daihuida{

    private int themeid=0;
    private int aid=123;
    private String contexts="";
//    private final static String HTML_URL = "http://www.buqi5.cn:8080/web/index5_submit.php?context=qwe&aid=12345&themeid=1";
    private  static String HTML_URL = "";
    //answer_list
    private int  flag=0;
    private ListView list_one;
    private MyAdapter_answer MyAdapter_answer = null;
    private List<Data> mData = null;
    private Context mContext = null;

    private String detail = "";
    private static String HTML_URL3 = "";

    // 用于刷新界面
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText( huidawentijiemian.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    Toast.makeText( huidawentijiemian.this, "回答成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    Toast.makeText( huidawentijiemian.this, "显示error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x004:
                    parseEasyJson(detail);
                    Toast.makeText( huidawentijiemian.this, "显示成功", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.huidajiemian);

        Intent intent=this.getIntent();
        Bundle bundle =intent.getExtras();
        String et=bundle.getString("name");
        themeid=bundle.getInt("aId");

        HTML_URL3="http://www.buqi5.cn:8080/web/index5_answer.php?aId="+themeid;

        TextView te=(TextView) findViewById(R.id.wenti);
        te.setText(et);

        final EditText login_id=(EditText)findViewById(R.id.login_id);

        mContext = huidawentijiemian.this;
        bindViews();
        mData = new LinkedList<Data>();
        MyAdapter_answer = new MyAdapter_answer((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(MyAdapter_answer);

        setViews();

        final Button tijiao=(Button)findViewById(R.id.tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contexts=login_id.getText().toString();
                setViews2();
                huidawentijiemian.this.finish();
            }
        });
        final Button tuichu=(Button)findViewById(R.id.tuichu);
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huidawentijiemian.this.finish();
            }
        });

    }

    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_test);
    }

    //接受服务器数据
    private void setViews(){
        new Thread() {
            public void run() {
                try {
                    detail = GetData.getHtml(HTML_URL3);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("TAG",e.getMessage());
                    handler.sendEmptyMessage(0x003);
                }
                handler.sendEmptyMessage(0x004);
            };
        }.start();
    }

    // 解析Json字符串:
    private void parseEasyJson(String json) {
//        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                Person person = new Person();
//                person.setId(i + "");
//                person.setName(jsonObject.getString("context"));
//                person.setAge(jsonObject.getString("aid"));
//                persons.add(person);
                //Log.e("Json", person.getName());
                //Log.e("Json", person.getAge());
                MyAdapter_answer.add(new Data(R.mipmap.ic_icon_qitao, jsonObject.getString("context")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送数据到服务器
    private void setViews2(){
        new Thread() {
            public void run() {
                try {
                    HTML_URL = "http://www.buqi5.cn:8080/web/index5_submit.php?context="+contexts+"&aid="+aid+"&themeid="+themeid;
                    detail = GetData.getHtml(HTML_URL);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("TAG",e.getMessage());
                    handler.sendEmptyMessage(0x001);
                }
                handler.sendEmptyMessage(0x002);
            };
        }.start();
    }
}
