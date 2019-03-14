package com.example.volunteer.Utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volunteer.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2018/11/6.
 */

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    /**普通商品的（normalHolder）的标题集合,调用者传入*/
    private List<String> normalGoodsTitls;
    /**头布局总数*/
    private int HEADER_CONUNT=7;

    public RvAdapter(List<String> normalGoodsTitls) {
        this.normalGoodsTitls = normalGoodsTitls;
    }

    private int HEADER0 = 0;
    private int HEADER1 = 1;
    private int HEADER2 = 2;
    private int HEADER3 = 3;
    private int HEADER4 = 4;
    private int HEADER5 = 5;
    private int HEADER6 = 6;
    private int NORMAL = 100;

    private View headView0;
    private View headView1;
    private View headView2;
    private View headView3;
    private View headView4;
    private View headView5;
    private View headView6;

    public void setHeadView0(View headView0) {
        this.headView0 = headView0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER0) {
            return new BannerHolder(headView0);
        }else if(viewType==HEADER1){
            return new GridMenuHolder(headView1);
        }else if (viewType==HEADER2){
            return new HeadlinesHolder(headView2);
        }else if(viewType==HEADER3){
            return new SnapUpHolder(headView3);
        }else if(viewType==HEADER4){
            return new MiddleBannerHolder(headView4);
        }else if(viewType==HEADER5){
            return new HotMatketHolder(headView5);
        }else if(viewType==HEADER6){
            return new GoodsTrendHolder(headView6);
        }
        else {
            return new NormalHolder(View.inflate(UIUtils.getContext(), R.layout.rv_item_normal,null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Log.d("alan","holder位置---》"+holder.getLayoutPosition());
        if (viewType == HEADER0 ) {
            return;
        }else if(viewType==HEADER1){
            return;
        }else if(viewType==HEADER2){
            return;
        }else if (viewType==HEADER3){
            return;
        }else if(viewType==HEADER4){
            return;
        }else if(viewType==HEADER5){
            return;
        }else if (viewType==HEADER6){
            return;
        }
        else if(viewType==NORMAL){
            NormalHolder normalHolder= (NormalHolder) holder;
            final int realPostion=position-HEADER_CONUNT;//获取真正的位置

            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = realPostion;
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = realPostion;
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return true;
                    }
                });
            }


//            Log.d("alan","位置-->"+position);
            normalHolder.tv_title.setText(normalGoodsTitls.get(realPostion));
            normalHolder.iv_goodsLeft.setOnClickListener(this);
            normalHolder.iv_goodsLeft.setTag(realPostion);
            normalHolder.iv_goodsRight.setOnClickListener(this);
            normalHolder.iv_goodsRight.setTag(realPostion);

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headView0 != null) {
            return HEADER0;
        }else if(position==1&&headView1!=null){
            return HEADER1;
        }else if(position==2&&headView2!=null){
            return HEADER2;
        }else if (position==3&&headView3!=null){
            return HEADER3;
        }else if (position==4&&headView4!=null){
            return HEADER4;
        }else if (position==5&&headView5!=null){
            return HEADER5;
        }else if(position==6&&headView6!=null){
            return HEADER6;
        }
        else {
            return NORMAL;
        }
    }

    //有7条普通数据，但是要加上Header的总数
    @Override
    public int getItemCount() {
        return normalGoodsTitls.size()+HEADER_CONUNT;
    }

    /**
     * 添加顶部banner
     */
    public void  addHeadView0(View view) {
        this.headView0 = view;
    }
    /**添加10个子菜单*/
    public void addHeaderView1(View v) {
        this.headView1 = v;
    }
    /**添加淘宝头条*/
    public void addHeaderView2(View v){
        this.headView2=v;
    }
    /**添加抢购的Header*/
    public void addHeaderView3(View v){
        this.headView3=v;
    }
    /**中间的banner*/
    public void addHeaderView4(View v){
        this.headView4=v;
    }
    public void addHeaderView5(View v){
        this.headView5=v;
    }
    public void addHeaderView6(View v){
        this.headView6=v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.iv_goodsLeft:
                int postion= (int) v.getTag();

                UIUtils.showToast("shoes left"+"\n"+"postion "+postion);
                break;
            case R.id.iv_goodsRight:
                int postion2= (int) v.getTag();

                UIUtils.showToast("shoes right"+"\n"+"postion "+postion2);
                break;
        }
    }

    //顶部banner
    class BannerHolder extends RecyclerView.ViewHolder {

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //10个子菜单
    class GridMenuHolder extends RecyclerView.ViewHolder{

        public GridMenuHolder(View itemView) {
            super(itemView);
        }
    }

    //普通的Holder
    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goodsLeft)
        ImageView iv_goodsLeft;
        @BindView(R.id.iv_goodsRight)
        ImageView iv_goodsRight;
        @BindView(R.id.tv_title)
        TextView tv_title;
        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    /**淘宝头条HOlder*/
    class HeadlinesHolder extends RecyclerView.ViewHolder{

        public HeadlinesHolder(View itemView) {
            super(itemView);
        }
    }
    /**抢购的Header(4个）*/
    class SnapUpHolder extends RecyclerView.ViewHolder{

        public SnapUpHolder(View itemView) {
            super(itemView);
        }
    }
    /**中间的banner*/
    class MiddleBannerHolder extends RecyclerView.ViewHolder{

        public MiddleBannerHolder(View itemView) {
            super(itemView);
        }
    }
    /**热门市场*/
    class HotMatketHolder extends RecyclerView.ViewHolder{
        public HotMatketHolder(View itemView) {
            super(itemView);
        }
    }
    /**热门市场下面的潮流商品*/
    class GoodsTrendHolder extends RecyclerView.ViewHolder{

        public GoodsTrendHolder(View itemView) {
            super(itemView);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v, int postion);
        void onItemLongClick(View v, int postion);
    }
    /**自定义条目点击监听*/
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
