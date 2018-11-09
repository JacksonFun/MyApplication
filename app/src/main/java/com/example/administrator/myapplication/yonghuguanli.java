package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2018\5\14 0014.
 */

public class yonghuguanli extends denglu{
    private int user_id;
    private String detail = "";
    private final static String HTML_URL3 = "http://www.buqi5.cn:8080/web/index2_update.php";

    // 用于刷新界面
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x001:
                    Toast.makeText( yonghuguanli.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    //parseEasyJson(detail);
                    Toast.makeText( yonghuguanli.this, "加载完毕", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        ;
    };

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.yonghuguanli);

        Intent intent=this.getIntent();
        Bundle bundle =intent.getExtras();
        final int user_id=bundle.getInt("user_id");

        final Button resetpwd_btn_sure=(Button)findViewById(R.id.resetpwd_btn_sure);
        resetpwd_btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.e("TAG", String.valueOf(user_id));

                //Log.d("TAG", String.valueOf(user_id));

                setViews();
                yonghuguanli.this.finish();
            }
        });
        final Button resetpwd_btn_cancel=(Button)findViewById(R.id.resetpwd_btn_cancel);
        resetpwd_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yonghuguanli.this.finish();
            }
        });
    }

    private void setViews(){
        new Thread() {
            public void run() {
                try {
                    final EditText login_id=(EditText)findViewById(R.id.resetpwd_edit_pwd_old);
                    final EditText login_password=(EditText)findViewById(R.id.resetpwd_edit_pwd_new);
                    final String loginid= login_id.getText().toString();
                    final String loginpassword=login_password.getText().toString();
                    Log.e("TAG", String.valueOf(loginid));
                    Log.e("TAG", String.valueOf(loginpassword));
                    if (login_id.equals(login_password)){
                        //detail = GetData.getHtml(HTML_URL3);
                        detail = PostUtils_update.LoginByPost(HTML_URL3,loginid,loginpassword,user_id);
                        Log.e("TAG", String.valueOf(user_id));
                        handler.sendEmptyMessage(0x002);
                    }else{
                        handler.sendEmptyMessage(0x001);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("TAG",e.getMessage());
                    handler.sendEmptyMessage(0x001);
                }
//                handler.sendEmptyMessage(0x002);
            };
        }.start();
    }
}
