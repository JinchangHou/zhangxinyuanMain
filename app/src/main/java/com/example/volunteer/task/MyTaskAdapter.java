package com.example.volunteer.task;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.volunteer.Javabean.MyTask;
import com.example.volunteer.LBS.LBSActivity;
import com.example.volunteer.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 29208 on 2019/4/8.
 */

public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.ViewHolder> {
    private List<MyTask> mTaskList=new ArrayList<>();
    private Context context;
    public MyTaskAdapter(Context context, List<MyTask> list) {
        this.context=context;
        this.mTaskList=list;

    }

    @Override
    public void onBindViewHolder(MyTaskAdapter.ViewHolder holder, int position) {
        final MyTask myTask=mTaskList.get(position);
        holder.publishMan.setText(myTask.getPublishMan()+"");
        holder.leibie.setText(myTask.getLeibie()+"");
        holder.title.setText(myTask.getTitle()+"");
        holder.time.setText(myTask.getStartTime()+"");
        Glide.with(context).load(myTask.getIcon()).into(holder.icon);
        Glide.with(context).load(myTask.getPicture()).into(holder.picture);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LBSActivity.class);
                context.startActivity(intent);
                if(ViewTaskActivity.class.isInstance(context)){
                    ViewTaskActivity activity=(ViewTaskActivity)context;
                    activity.finish();
                }
            }
        });
        if(myTask.getIsexed()==true){
            holder.view.setBackgroundColor(R.color.black);
            holder.state.setText("已完成");
        }

    }

    @Override
    public MyTaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_task_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView picture;
        TextView title;
        TextView leibie;
        TextView time;
        CircleImageView icon;
        TextView state;
        TextView publishMan;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            picture=itemView.findViewById(R.id.picture);
            title=itemView.findViewById(R.id.title);
            leibie=itemView.findViewById(R.id.leibie);
            time=itemView.findViewById(R.id.time);
            icon=itemView.findViewById(R.id.icon);
            state=itemView.findViewById(R.id.task_state);
            publishMan=itemView.findViewById(R.id.publish_man);
        }
    }
}
