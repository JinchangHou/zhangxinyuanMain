package com.example.volunteer.Home_Page;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.volunteer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29208 on 2018/11/15.
 */
public class FruitAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Fruit> mFruitList;
    private List<Integer>heights;
    public FruitAdapter(Context mContext, List<Fruit>mFruitList) {
        this.mContext=mContext;
        this.mFruitList=mFruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Intent intent=new Intent(mContext,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                Log.d("adapter",fruit.getImageId()+"");
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    private void getRandomHeight(){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < mFruitList.size(); i++) {
            heights.add((int)(200+Math.random()*400)); Log.d("heights",heights.get(i).toString());
        }

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //实现瀑布流
//        getRandomHeight();
//
//        ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
//        params.height = heights.get(position);//把随机的高度赋予item布局
//        holder.itemView.setLayoutParams(params);//把params设置给item布局

        ViewHolder holder11=(ViewHolder)holder;
        Fruit fruit=mFruitList.get(position);
        holder11.fruit_name.setText(fruit.getName());
        Glide.with(mContext).load(R.drawable.timg).into(holder11.fruit_image);
        Log.d("adapter1", fruit.getImageId() + "");
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruit_image;
        TextView fruit_name;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            fruit_image= (ImageView) itemView.findViewById(R.id.fruit_image);
            fruit_name= (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
