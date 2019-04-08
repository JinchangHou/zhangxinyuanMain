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
import com.example.volunteer.Javabean.Task;
import com.example.volunteer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29208 on 2019/4/6.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTaskList=new ArrayList<>();
    private Context context;
    public TaskAdapter( Context context,List<Task>list) {
        this.context=context;
        mTaskList=list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final  Task task =mTaskList.get(position);
        holder.value.setText(""+task.getValue()+"个爱心币");
        Glide.with(context).load(task.getIcon()).into(holder.icon);
        holder.publishMan.setText(task.getPublishMan());
        holder.nowMan_needMan.setText("剩余"+(task.getNeedMan()-task.getNowMan())+"人");
        holder.nowMan.setText("已报"+task.getNowMan()+"人");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TaskContentActivity.class);

                intent.putExtra("position",position);
                context.startActivity(intent);
//                if(GetTaskActivity.class.isInstance(context)){
//                    GetTaskActivity activity =(GetTaskActivity)context;
//                    activity.finish();
//                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mTaskList.size();

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView publishMan;
        TextView nowMan;
        TextView nowMan_needMan;
        TextView value;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            icon=itemView.findViewById(R.id.icon);
            publishMan=itemView.findViewById(R.id.publishMan);
            nowMan=itemView.findViewById(R.id.nowmanman);
            nowMan_needMan=itemView.findViewById(R.id.Man);
            value=itemView.findViewById(R.id.aixinbixxx);
        }
    }
}
