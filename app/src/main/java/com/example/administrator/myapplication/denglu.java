package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class denglu extends denglujiemian {
    private  int user_id;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.denglu);

        Intent intent=this.getIntent();
        Bundle bundle =intent.getExtras();
        final int user_id=bundle.getInt("user_id");

        final Button tijiao=(Button)findViewById(R.id.tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(denglu.this, tijiaowenti.class);
                Intent intent =new Intent();
                Bundle bundle =new Bundle();
                bundle.putInt("user_id",user_id);
                //Log.d("TAG", String.valueOf(user_id));
                intent.putExtras(bundle);
                intent.setClass(denglu.this, tijiaowenti.class);
                startActivity(intent);
            }
        });
        final Button guanli=(Button)findViewById(R.id.guanli);
        guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(denglu.this, yonghuguanli.class);
//                startActivity(intent);
                Intent intent =new Intent();
                Bundle bundle =new Bundle();
                bundle.putInt("user_id",user_id);
                //Log.d("TAG", String.valueOf(user_id));
                intent.putExtras(bundle);
                intent.setClass(denglu.this, yonghuguanli.class);
                startActivity(intent);
            }
        });
        final Button huida=(Button)findViewById(R.id.huida);
        huida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(denglu.this, daihuida.class);
//                startActivity(intent);
                Intent intent =new Intent();
                Bundle bundle =new Bundle();
                bundle.putInt("user_id",user_id);
                //Log.d("TAG", String.valueOf(user_id));
                intent.putExtras(bundle);
                intent.setClass(denglu.this, daihuida.class);
                startActivity(intent);
            }
        });
        final Button jiejue=(Button)findViewById(R.id.jiejue);
        jiejue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(denglu.this, yijiejue.class);
//                startActivity(intent);
                Intent intent =new Intent();
                Bundle bundle =new Bundle();
                bundle.putInt("user_id",user_id);
                //Log.d("TAG", String.valueOf(user_id));
                intent.putExtras(bundle);
                intent.setClass(denglu.this, yijiejue.class);
                startActivity(intent);
            }
        });
        final Button myquestion=(Button)findViewById(R.id.myquestion);
        myquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(denglu.this, myquestion_list.class);
//                startActivity(intent);
                Intent intent =new Intent();
                Bundle bundle =new Bundle();
                bundle.putInt("user_id",user_id);
                //Log.d("TAG", String.valueOf(user_id));
                intent.putExtras(bundle);
                intent.setClass(denglu.this, myquestion_list.class);
                startActivity(intent);
            }
        });
    }
}
