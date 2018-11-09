package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class denglujiemian extends AppCompatActivity {


    private TextView txtMenu, txtshow;
    private LinearLayout tMenu;
    private ImageView imgPic;
    private WebView webView;
    private ScrollView scroll;
    private Bitmap bitmap;
    private String detail = "";
    private boolean flag = false;
    private final static String PIC_URL = "http://ww2.sinaimg.cn/large/7a8aed7bgw1evshgr5z3oj20hs0qo0vq.jpg";
    private final static String HTML_URL = "http://www.buqi5.cn:8080/web/index.php?name=123&password=123";
    private final static String HTML_URL2 = "http://www.buqi5.cn:8080/web/index2.php";
    private final static String HTML_URL2zhuce = "http://www.buqi5.cn:8080/web/index2_zhuce.php";

    // 用于刷新界面
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText( denglujiemian.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    //hideAllWidget();
//                    scroll.setVisibility(View.VISIBLE);
//                    txtshow.setText(detail);
                    parseEasyJson(detail);
                    Toast.makeText( denglujiemian.this, "加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    Toast.makeText( denglujiemian.this, "success", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    // 定义一个隐藏所有控件的方法:
    private void hideAllWidget() {
        imgPic.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglujiemian);
        final EditText login_id=(EditText)findViewById(R.id.loginid);
        final EditText login_password=(EditText)findViewById(R.id.loginpassword);


        //final CharSequence lid=login_id.getText();
        //final CharSequence lpasswd=login_password.getText();


        Button denglu=(Button)findViewById(R.id.denglu);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(denglujiemian.this,denglu.class);
//                startActivity(intent);
                // 传递消息
                final String loginid= login_id.getText().toString();
                final String loginpassword=login_password.getText().toString();
                new Thread() {
                    public void run() {
                        try {
                            detail = PostUtils.LoginByPost(HTML_URL2,loginid,loginpassword);
                            //Log.d("TAG",loginid);
                           // Log.e("TAG","a"+loginpassword);

                            //detail = PostUtils.LoginByPost(HTML_URL2,"123","123");
                            //detail = GetData.getHtml(HTML_URL);
                            Log.d("TAG",detail);

                            JSONObject jsonObject =new JSONObject(detail);
                            //Log.d("TAG",jsonObject.getString("name"));
                            Intent intent =new Intent();
                            Bundle bundle =new Bundle();
                            bundle.putInt("user_id",jsonObject.getInt("id"));//themeid
                            intent.putExtras(bundle);
                            intent.setClass(denglujiemian.this,denglu.class);
                            startActivity(intent);

                            //detail=detail.trim();
                            //Log.d("TAG", String.valueOf(detail.charAt(0)));

//                            if(detail.equals("success")){
//                                Intent intent=new Intent(denglujiemian.this,denglu.class);
//                                startActivity(intent);
//                            }else{
//                                handler.sendEmptyMessage(0x001);
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            //Log.e("TAG",e.getMessage());
                        }
                        //handler.sendEmptyMessage(0x002);
                    };
                }.start();

                //txtshow.setText(detail);

            }
        });
        //zhuce
        Button zhuce=(Button)findViewById(R.id.zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(denglujiemian.this,denglu.class);
//                startActivity(intent);
                // 传递消息
                final String loginid= login_id.getText().toString();
                final String loginpassword=login_password.getText().toString();
                new Thread() {
                    public void run() {
                        try {
                            detail = PostUtils.LoginByPost(HTML_URL2zhuce,loginid,loginpassword);
                            //Log.d("TAG",loginid);
                            // Log.e("TAG","a"+loginpassword);

                            //detail = PostUtils.LoginByPost(HTML_URL2,"123","123");
                            //detail = GetData.getHtml(HTML_URL);
                            Log.d("TAG",detail);

                            //Log.d("TAG",jsonObject.getString("name"));

                            detail=detail.trim();
                            //Log.d("TAG", String.valueOf(detail.charAt(0)));

                            if(detail.equals("error")){
                                handler.sendEmptyMessage(0x001);
                            }else{
                                handler.sendEmptyMessage(0x003);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            //Log.e("TAG",e.getMessage());
                        }
                        //handler.sendEmptyMessage(0x002);
                    };
                }.start();

                //txtshow.setText(detail);

            }
        });

    }
    // 解析Json字符串:
    private void parseEasyJson(String json){
        ArrayList<Person> persons = new ArrayList<Person>();
        try{
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Person person = new Person();
//                person.setId(i+"");
                person.setName(jsonObject.getString("name"));
//                person.setAge(jsonObject.getString("age"));
//                persons.add(person);
                Log.e("Json", person.getName());
//                Log.e("Json", person.getAge());

                Intent intent =new Intent();
                Bundle bundle =new Bundle();
//                String uer_id=jsonObject.getString("id");
                bundle.putInt("user_id",jsonObject.getInt("id"));//themeid
                intent.putExtras(bundle);
                intent.setClass(denglujiemian.this,denglu.class);
                startActivity(intent);
                handler.sendEmptyMessage(0x001);

            }
        }catch (Exception e){e.printStackTrace();}
    }
}

