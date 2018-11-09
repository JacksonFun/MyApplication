package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by sunjie on 2018/5/13.
 */

public class MyAdapter_answer extends BaseAdapter {

    private Context mContext;
    private LinkedList<Data> mData;

    public MyAdapter_answer() {}

    public MyAdapter_answer(LinkedList<Data> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
            holder = new ViewHolder();
            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img_icon.setImageResource(mData.get(position).getImgId());
        holder.txt_content.setText(mData.get(position).getContent());
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                if (position==0){
                if (mData.get(position).getbId()==1){
                    Intent intent =new Intent();
                    Bundle bundle =new Bundle();
                    bundle.putString("name",mData.get(position).getContent());
                    bundle.putInt("aId",mData.get(position).getaId());//themeid
                    intent.putExtras(bundle);
                    intent.setAction("my_action_question");
                    intent.addCategory("my_category_question");

                    mContext.startActivity(intent);
                }else {
                    Intent intent =new Intent();
                    Bundle bundle =new Bundle();
                    bundle.putString("name",mData.get(position).getContent());
                    bundle.putInt("aId",mData.get(position).getaId());//themeid
                    intent.putExtras(bundle);
                    intent.setAction("my_action_answer");
                    intent.addCategory("my_category_answer");

                    mContext.startActivity(intent);
                }

//                }
            }
        });
        return convertView;
    }

    private class ViewHolder{
        ImageView img_icon;
        TextView txt_content;
    }

    public void add(Data data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

}