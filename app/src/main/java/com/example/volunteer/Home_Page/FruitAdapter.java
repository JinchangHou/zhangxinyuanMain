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

import java.util.List;

/**
 * Created by 29208 on 2018/11/15.
 */
public class FruitAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Fruit> mFruitList;

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


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder11=(ViewHolder)holder;
        Fruit fruit=mFruitList.get(position);
        holder11.fruit_name.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder11.fruit_image);
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
