package com.example.volunteer.shuoshuo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.volunteer.R;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 29208 on 2018/8/10.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private int mPosition;
    public List<Content> mContentList;
    private Context context;
    public ContentAdapter(List<Content> mContentList, Context context) {
    //把所有的数据源传进来，并放在list当中
        this.mContentList = mContentList;
        this.context=context;

}



    @Override//用于创建ViewHolder实例
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        Log.d("adapter",mContentList.equals(null)+"=="+mContentList.size());
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shsuoshuo_recyclerview_item,parent,false);
//    private LinearLayout ll=view.findViewById(R.id.itemview);
//
        final ViewHolder holder=new ViewHolder(view,context,mContentList);

        //定义一个ViewHolder
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                int Position=holder.getAdapterPosition();
//
//                Intent intent =new Intent (context,ContentActivity.class);
//                context.startActivity(intent);
//                Toast.makeText(context,"djks",Toast.LENGTH_SHORT).show();
//               ContentActivity ca=new ContentActivity();
//                ca.actionStart(context,mContentList.get(position).getImageId(),
//                        mContentList.get(position).getContent());
            }
        });
        return holder;
    }



    @Override//用于对RecyclerVIew子项进行赋值，自动调用，自动传入position也就是index
    public void onBindViewHolder(ViewHolder holder, int position) {





//        Content content =mContentList.get(position);
//        holder.contentImage.setImageResource(content.getImageId());
        Glide.with(context).load( R.drawable.item).into(holder.contentImage);
        Glide.with(context).load(R.drawable.item).into(holder.iconImage);

        final Content content=mContentList.get(position);
        holder.time.setText(content.getTime());
        Glide.with(context).load(content.getPersonIcon()).into(holder.iconImage);
        Glide.with(context).load(content.getImageUrl()).into(holder.contentImage);
        holder.contentName.setText(content.getTitle());
        holder.countZan.setText(content.getCountZan()+"人觉得很赞");
        holder.personName.setText(content.getName());

        //倒序添加评论
        List<Comment>commentList=mContentList.get(position).getCommitList();
        Log.d("adapter", "commentlist=" + commentList.size());
        holder.comment1.setText(commentList.get(commentList.size() - 1).getName() + "  "
                + commentList.get(commentList.size() - 1).getContent());
        holder.comment2.setText(commentList.get(commentList.size()-2).getName()+"  "
                +commentList.get(commentList.size()-2).getContent());

    }

    @Override
    public int getItemCount() {
        return mContentList.size();

    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        View contentView;
        ImageView iconImage; TextView personName;
        TextView contentName; ImageView contentImage;
        TextView countZan;Button zan;Button commit;
        //三个评论
        TextView comment1,comment2,comment3;



        TextView time;
        public ViewHolder(final View itemView, final Context context, final List<Content> mContentList) {
            super(itemView);
            contentView=itemView;
            contentImage=itemView.findViewById(R.id.item_image);
            contentName=itemView.findViewById(R.id.item_content);
            iconImage=itemView.findViewById(R.id.icon_image);
            personName=itemView.findViewById(R.id.person_name);
            zan=itemView.findViewById(R.id.zan);
            commit=itemView.findViewById(R.id.commit);
            countZan=itemView.findViewById(R.id.zan_count);
            time=itemView.findViewById(R.id.time);

            comment1=itemView.findViewById(R.id.comment_display_tv1);
            comment2=itemView.findViewById(R.id.comment_display_tv2);
            comment3=itemView.findViewById(R.id.comment_display_tv3);


            //设置点赞的颜色变化

            zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(zan.isSelected()){
                        zan.setSelected(false);

                    }
                    else{
                        zan.setSelected(true);
                        mContentList.get(getAdapterPosition()).setCountZan(mContentList.get(getAdapterPosition()).getCountZan() + 1);
                        countZan.setText(mContentList.get(getAdapterPosition()).getCountZan() + "人觉得很赞");
                        Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
                        initComment(mContentList, context);
                    }
                }
            });
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view=inflater.inflate(R.layout.dialog_comment, (ViewGroup) itemView.findViewById(R.id.dialog));
                    final EditText text=view.findViewById(R.id.comment_enditext);
                    Toast.makeText(context, "comment", Toast.LENGTH_SHORT).show();

                     AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("写评论").setView(view).setNegativeButton("取消", null);
                    builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String commentContent = text.getText().toString().trim();

                            Log.d("adapter", "text=" + commentContent);
                            String myName = mContentList.get(getAdapterPosition()).getMyName();
                            mContentList.get(getAdapterPosition()).appendCommitList(new Comment(myName, commentContent));



                            //瞬间刷新
                            List<Comment>commentList=mContentList.get(getAdapterPosition()).getCommitList();
                            comment1.setText(commentList.get(commentList.size() - 1).getName() + "："
                                    + commentList.get(commentList.size() - 1).getContent());
                            comment2.setText(commentList.get(commentList.size() - 2).getName() + "："
                                    + commentList.get(commentList.size() - 2).getContent());



                            Toast.makeText(context,"评论成功",Toast.LENGTH_SHORT).show();
                            initComment(mContentList, context);

                        }
                    });
                   AlertDialog dialog=builder.create();
                    dialog.show();



                }
            });
        }



        private void initComment(List<Content> contentList, final Context context){



            Content content=contentList.get(getAdapterPosition());
            String objId=content.getObjectId();
            content.update(objId, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
//                        Toast.makeText(context,"评论成功",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
