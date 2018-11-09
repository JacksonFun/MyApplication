package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class chakanjiemian extends yijiejue{

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
                    Toast.makeText( chakanjiemian.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    parseEasyJson(detail);
                    Toast.makeText( chakanjiemian.this, "加载完毕", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chakanjiemian);

        Intent intent=this.getIntent();
        Bundle bundle =intent.getExtras();
        String et=bundle.getString("name");
        int eet=bundle.getInt("aId");//themeid
        HTML_URL3="http://www.buqi5.cn:8080/web/index5_answer.php?aId="+eet;
        TextView te=(TextView) findViewById(R.id.wenti);
        te.setText(et);
        Log.e("Json", HTML_URL3);

        mContext = chakanjiemian.this;
        bindViews();
        mData = new LinkedList<Data>();
        MyAdapter_answer = new MyAdapter_answer((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(MyAdapter_answer);

        setViews();

        final Button wancheng=(Button)findViewById(R.id.wancheng);
        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chakanjiemian.this.finish();
            }
        });

    }

    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_test);
    }

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
    private void parseEasyJson(String json) {
        ArrayList<Person> persons = new ArrayList<Person>();
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
}
