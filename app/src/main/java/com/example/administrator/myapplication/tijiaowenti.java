package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class tijiaowenti extends denglu {

//    private int themeid=0;
    private int aid=123;
    private String contexts="";
    //    private final static String HTML_URL = "http://www.buqi5.cn:8080/web/index5_submit.php?context=qwe&aid=12345&themeid=1";
    private  static String HTML_URL = "";
    //answer_list
//    private int  flag=0;
//    private ListView list_one;
//    private MyAdapter_answer MyAdapter_answer = null;
//    private List<Data> mData = null;
//    private Context mContext = null;
//
    private String detail = "";
//    private static String HTML_URL3 = "";

    // 用于刷新界面
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText( tijiaowenti.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    Toast.makeText( tijiaowenti.this, "成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    Toast.makeText( tijiaowenti.this, "显示error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x004:
                    //parseEasyJson(detail);
                    Toast.makeText( tijiaowenti.this, "显示成功", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.tijiaowenti);

        Intent intent=this.getIntent();
        Bundle bundle =intent.getExtras();
        aid =bundle.getInt("user_id");

        final Button queding=(Button)findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText login_id=(EditText)findViewById(R.id.login_id2);
                contexts=login_id.getText().toString();

                Log.e("TAG",contexts);
                setViews2();
                tijiaowenti.this.finish();
            }
        });
        final Button fanhui=(Button)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tijiaowenti.this.finish();
            }
        });

    }
    //发送数据到服务器
    private void setViews2(){
        new Thread() {
            public void run() {
                try {
                    HTML_URL = "http://www.buqi5.cn:8080/web/index5_submitquestion.php?theme="+contexts+"&qid="+aid;
                    Log.e("TAG",HTML_URL);
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

