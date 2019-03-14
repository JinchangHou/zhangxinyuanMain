package com.example.volunteer.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.volunteer.R;
import com.example.volunteer.Utils.RvAdapter;
import com.example.volunteer.Utils.UIUtils;
import com.example.volunteer.ui.ButtomBtn;
import com.example.volunteer.ui.DividerGridItemDecoration;
import com.example.volunteer.ui.FlyBanner;
import com.example.volunteer.ui.GridMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MallActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, RvAdapter.OnItemClickListener{

    /**普通商品的（normalHolder）的标题集合*/
    private List<String> normalGoodsTitls=new ArrayList<>();
    private String[] gridMenuTitles=new String[]{"天猫","聚划算","天猫国际","外卖","天猫超市","充值中心","飞猪旅行","领金币","到家","分类"};
    //代替findViewbyid（）方法

    /*@BindView(R.id.buttomBtnScan)
    ButtomBtn buttomBtnScan;*/

    /*@BindView(R.id.bomBtnMsg)
    ButtomBtn bomBtnMsg;*/

    /*@BindView(R.id.bomBtnHome)
    ButtomBtn bomBtnHome;*/

    /*@BindView(R.id.bomBtnTiny)
    ButtomBtn bomBtnTiny;*/

    /*@BindView(R.id.bomBtnAsk)
    ButtomBtn bomBtnAsk;*/

    /*@BindView(R.id.bomBtnShopCar)
    ButtomBtn bomBtnShopCar;*/

    /*@BindView(R.id.bomBtnMy)
    ButtomBtn bomBtnMy;*/

    /*@BindView(R.id.swp)
    SwipeRefreshLayout swp;*/

    /*@BindView(R.id.rv)
    RecyclerView rv;*/
    private Handler mHandler;
    private List<String> bigPics;
    private List<String> smallPics=new ArrayList<>();
    private ButtomBtn buttomBtnScan;
    private ButtomBtn bomBtnMsg;
    private ButtomBtn bomBtnHome;
    private ButtomBtn bomBtnTiny;
    private ButtomBtn bomBtnAsk;
    private ButtomBtn bomBtnShopCar;
    private ButtomBtn bomBtnMy;
    private SwipeRefreshLayout swp;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        buttomBtnScan = (ButtomBtn)findViewById(R.id.buttomBtnScan);
        bomBtnMsg = (ButtomBtn)findViewById(R.id.bomBtnMsg);
        bomBtnHome = (ButtomBtn)findViewById(R.id.bomBtnHome);
        bomBtnTiny = (ButtomBtn)findViewById(R.id.bomBtnTiny);
        bomBtnAsk = (ButtomBtn)findViewById(R.id.bomBtnAsk);
        bomBtnShopCar = (ButtomBtn)findViewById(R.id.bomBtnShopCar);
        bomBtnMy = (ButtomBtn)findViewById(R.id.bomBtnMy);
        swp = (SwipeRefreshLayout)findViewById(R.id.swp);
        rv = (RecyclerView)findViewById(R.id.rv);


        //ButterKnife.bind(this);
        initNormalGoodsTitls();
        initBigPics();
        initSmallPics();
        mHandler = new Handler();
        initTopBtn();
        initBomBtn();
        initRv();
    }

    private void initNormalGoodsTitls() {
        //7个
        normalGoodsTitls.add("质男说");
        normalGoodsTitls.add("户外休闲");
        normalGoodsTitls.add("型男社");
        normalGoodsTitls.add("鲜肉潮搭");
        normalGoodsTitls.add("小小少年");
        normalGoodsTitls.add("爱车装扮");
        normalGoodsTitls.add("霸道总裁");
    }


    private void initBigPics() {
        bigPics = new ArrayList<>();
        bigPics.add("https://gd2.alicdn.com/imgextra/i2/380101244/TB2HHzZdNmJ.eBjy0FhXXbBdFXa_!!380101244.jpg");
        bigPics.add("https://gd4.alicdn.com/imgextra/i4/380101244/TB2qUNua4mI.eBjy0FlXXbgkVXa_!!380101244.jpg");
        bigPics.add("https://gd1.alicdn.com/imgextra/i1/380101244/TB2REFwa9qJ.eBjy1zbXXbx8FXa_!!380101244.jpg");
        bigPics.add("https://gd4.alicdn.com/imgextra/i4/380101244/TB2Ye4taZeK.eBjSszgXXczFpXa_!!380101244.jpg");
        bigPics.add("http://ob9thtnhs.bkt.clouddn.com/tuisong/da386d4d7872451ca346ba6e37da17b6.jpg?e=1477544913&token=m2BF8x75sZF4DIfwnxFri5sT51HeuFRmU2Ue0uVf:LWGBH77qhKA_BEcdgUA5u5AebR0=");
    }
    private void initSmallPics() {
        smallPics.add("http://ob9thtnhs.bkt.clouddn.com/tuisong/37895e3d741e4c17a032781e33e76507.jpg?e=1477622637&token=m2BF8x75sZF4DIfwnxFri5sT51HeuFRmU2Ue0uVf:U1V7RIOesNu_pz2r48LpaXXlYUo=");
        smallPics.add("http://ob9thtnhs.bkt.clouddn.com/tuisong/7b7256de5e854d7fa842e5ae72f98a74.jpg?e=1477544893&token=m2BF8x75sZF4DIfwnxFri5sT51HeuFRmU2Ue0uVf:yaDTDbhakBFP22cy0eAfHjPfTB4=");

    }
    private void initTopBtn() {
        buttomBtnScan.setIvAndTv(R.drawable.mscan, "扫一扫");
        buttomBtnScan.setTvColor(Color.WHITE);
        bomBtnMsg.setIvAndTv(R.mipmap.mcomment, "消息");
        bomBtnMsg.setTvColor(Color.WHITE);
    }
    private void initBomBtn() {
        bomBtnHome.setIvAndTv(R.mipmap.home_fill,"首页");
        bomBtnHome.setTvColor(Color.parseColor("#d81e06"));

        bomBtnTiny.setIvAndTv(R.mipmap.we,"微淘");
        bomBtnTiny.setTvColor(getResources().getColor(R.color.font33));

        bomBtnAsk.setIvAndTv(R.mipmap.ask,"问大家");
        bomBtnAsk.setTvColor(getResources().getColor(R.color.font33));

        bomBtnShopCar.setIvAndTv(R.mipmap.cart,"购物车");
        bomBtnShopCar.setTvColor(getResources().getColor(R.color.font33));

        bomBtnMy.setIvAndTv(R.mipmap.my,"我的淘宝");
        bomBtnMy.setTvColor(getResources().getColor(R.color.font33));
    }
    private void initRv() {
        swp.setOnRefreshListener(this);
        RvAdapter rvAdapter = new RvAdapter(normalGoodsTitls);
        rvAdapter.setmOnItemClickLitener(this);

        initBannerTop(rvAdapter);
        initGridMenu(rvAdapter);
        initHeadLines(rvAdapter);
        initSnapUp(rvAdapter);
        initMiddleBannner(rvAdapter);
        initHotMarket(rvAdapter);
        initGoodsTrend(rvAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion == 0) {
                    return 2;
                }else if(postion==1){
                    return 2;
                }else if (postion==2){
                    return 2;
                }else if (postion==3){
                    return 2;
                }else if (postion==4){
                    return 2;
                }else if (postion==5){
                    return 2;
                }else if (postion==6){
                    return 2;
                }
                else {
                    return 1;
                }
            }
        });
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(rvAdapter);
        rv.addItemDecoration(new DividerGridItemDecoration(this));

    }
    private void initBannerTop(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(UIUtils.getContext(), R.layout.banner_top, null);
        FlyBanner bannerTop= (FlyBanner) bannerBigView.findViewById(R.id.bannerTop);
        rvAdapter.addHeadView0(bannerBigView);
        bannerTop.setImagesUrl(bigPics);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtils.showToast("position--->"+position);
            }
        });
    }
    /**初始化10个子菜单*/
    private void initGridMenu(RvAdapter rvAdapter) {
        View gridMenu = View.inflate(UIUtils.getContext(), R.layout.grid_menu_10, null);

        List<GridMenu> gridMenus=new ArrayList<>();
        GridMenu gridCat= (GridMenu) gridMenu.findViewById(R.id.gridCat);
        GridMenu gridJHS= (GridMenu) gridMenu.findViewById(R.id.gridJHS);
        GridMenu gridTMgj= (GridMenu) gridMenu.findViewById(R.id.gridTMgj);
        GridMenu grid_wm= (GridMenu) gridMenu.findViewById(R.id.grid_wm);
        GridMenu grid_tmcs= (GridMenu) gridMenu.findViewById(R.id.grid_tmcs);

        GridMenu grid_czzx= (GridMenu) gridMenu.findViewById(R.id.grid_czzx);
        GridMenu grid_fzlx= (GridMenu) gridMenu.findViewById(R.id.grid_fzlx);
        GridMenu grid_ljb= (GridMenu) gridMenu.findViewById(R.id.grid_ljb);
        GridMenu grid_dj= (GridMenu) gridMenu.findViewById(R.id.grid_dj);
        GridMenu grid_fl= (GridMenu) gridMenu.findViewById(R.id.grid_fl);

        gridMenus.add(gridCat);
        gridMenus.add(gridJHS);
        gridMenus.add(gridTMgj);
        gridMenus.add(grid_wm);
        gridMenus.add(grid_tmcs);
        gridMenus.add(grid_czzx);
        gridMenus.add(grid_fzlx);
        gridMenus.add(grid_ljb);
        gridMenus.add(grid_dj);
        gridMenus.add(grid_fl);

        initGridMenuAttr(gridMenus);
        initOnCLick(gridMenus);
        rvAdapter.addHeaderView1(gridMenu);

    }
    /**初始化淘宝头条*/
    private void initHeadLines(RvAdapter rvAdapter) {
        View headLineView = View.inflate(UIUtils.getContext(), R.layout.tao_bao_headline, null);
        LinearLayout ll_headline= (LinearLayout) headLineView.findViewById(R.id.ll_headline);
        ll_headline.setOnClickListener(this);
        rvAdapter.addHeaderView2(headLineView);
    }
    /**初始化抢购*/
    private void initSnapUp(RvAdapter rvAdapter) {
        View snapUpView = View.inflate(UIUtils.getContext(), R.layout.snapup_layout, null);
        snapUpView.findViewById(R.id.ll_qtg).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_yhh).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_agj).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_bmqd).setOnClickListener(this);

        rvAdapter.addHeaderView3(snapUpView);
    }
    /**初始化中间的banner*/
    private void initMiddleBannner(RvAdapter rvAdapter) {
        View middleBannerView = View.inflate(UIUtils.getContext(), R.layout.banner_middle, null);
        FlyBanner bannerMiddle= (FlyBanner) middleBannerView.findViewById(R.id.bannerMiddle);
        rvAdapter.addHeaderView4(middleBannerView);
        bannerMiddle.setImagesUrl(smallPics);
        bannerMiddle.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtils.showToast("position--->"+position);
            }
        });
    }
    private void initHotMarket(RvAdapter rvAdapter) {
        View hotMarketView = View.inflate(UIUtils.getContext(), R.layout.hot_market, null);
        hotMarketView.findViewById(R.id.rl_hotMarket).setOnClickListener(this);
        rvAdapter.addHeaderView5(hotMarketView);
    }
    /**初始化潮流商品*/
    private void initGoodsTrend(RvAdapter rvAdapter) {
        View goodsTrendView = View.inflate(UIUtils.getContext(), R.layout.goods_trend, null);
        goodsTrendView.findViewById(R.id.goodsSectionIfashion).setOnClickListener(this);
        goodsTrendView.findViewById(R.id.goodsSectionCWC).setOnClickListener(this);
        goodsTrendView.findViewById(R.id.goodsSectionqbb).setOnClickListener(this);
        goodsTrendView.findViewById(R.id.goodsSectionZqtd).setOnClickListener(this);

        rvAdapter.addHeaderView6(goodsTrendView);
    }
    /**设置10个子菜单的图片和文字*/
    private void initGridMenuAttr(List<GridMenu> gridMenus) {
        for (int i = 0; i < gridMenus.size(); i++) {
            GridMenu gridMenu = gridMenus.get(i);
            gridMenu.setAttr(R.mipmap.art_collection,gridMenuTitles[i]);
        }
    }
    private void initOnCLick(List<GridMenu> gridMenus) {
        for (GridMenu gridMenu : gridMenus) {
            gridMenu.setOnClickListener(this);
        }
    }
    @OnClick(R.id.buttomBtnScan)
    public void clickScan(View v) {
        UIUtils.showToast("扫一扫");
    }

    @OnClick(R.id.bomBtnMsg)
    public void clickMsg(View v) {
        UIUtils.showToast("消息");
    }

    @OnClick(R.id.ll_search)
    public void clickSearch(View v) {
        UIUtils.showToast("搜索");
    }

    @OnClick(R.id.iv_photo)
    public void clickPhoto(View v) {
        UIUtils.showToast("拍照");
    }


    @OnClick(R.id.bomBtnHome)
    public void bomBtnHome(View v) {
        UIUtils.showToast("首页");
    }
    @OnClick(R.id.bomBtnTiny)
    public void bomBtnTiny(View v) {
        UIUtils.showToast("微淘");
    }
    @OnClick(R.id.bomBtnAsk)
    public void bomBtnAsk(View v) {
        UIUtils.showToast("问大家");
    }
    @OnClick(R.id.bomBtnShopCar)
    public void bomBtnShopCar(View v) {
        UIUtils.showToast("购物车");
    }
    @OnClick(R.id.bomBtnMy)
    public void bomBtnMy(View v) {
        UIUtils.showToast("我的淘宝");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swp.setRefreshing(false);
            }
        }, 1500);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gridCat://天猫
                UIUtils.showToast("天猫");
                Intent intent = new Intent(MallActivity.this,DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.gridJHS://聚划算
                UIUtils.showToast("聚划算");
                break;
            case R.id.gridTMgj://天猫国际
                UIUtils.showToast("天猫国际");
                break;
            case R.id.grid_wm://外卖
                UIUtils.showToast("外卖");
                break;
            case R.id.grid_tmcs://天猫超市
                UIUtils.showToast("天猫超市");
                break;
            case R.id.grid_czzx://充值中心
                UIUtils.showToast("充值中心");
                break;
            case R.id.grid_fzlx://飞猪旅行
                UIUtils.showToast("飞猪旅行");
                break;
            case R.id.grid_ljb://领金币
                UIUtils.showToast("领金币");
                break;
            case R.id.grid_dj://到家
                UIUtils.showToast("到家");
                break;
            case R.id.grid_fl://分类
                UIUtils.showToast("分类");
                break;
            case R.id.ll_headline://淘宝头条
                UIUtils.showToast("淘宝头条");
                break;
            case R.id.ll_qtg:
                UIUtils.showToast("淘抢购");
                break;
            case R.id.ll_yhh:
                UIUtils.showToast("有好货");
                break;
            case R.id.ll_agj:
                UIUtils.showToast("爱逛街");
                break;
            case R.id.ll_bmqd:
                UIUtils.showToast("必买清单");
                break;
            case R.id.rl_hotMarket:
                UIUtils.showToast("热门市场");
                break;
            case R.id.goodsSectionIfashion:
            case R.id.goodsSectionCWC:
            case R.id.goodsSectionqbb:
            case R.id.goodsSectionZqtd:
                String msg="";
                if (v.getId()==R.id.goodsSectionIfashion){
                    msg="iFashion";
                }else if(v.getId()==R.id.goodsSectionCWC){
                    msg="潮玩城";
                }else if(v.getId()==R.id.goodsSectionqbb){
                    msg="亲宝贝";
                }else if(v.getId()==R.id.goodsSectionZqtd){
                    msg="足球天地";
                }
                UIUtils.showToast(msg);
                break;
        }
    }

    @Override
    public void onItemClick(View v, int postion) {
        UIUtils.showToast("item click postion "+postion);
    }

    @Override
    public void onItemLongClick(View v, int postion) {
        UIUtils.showToast("item long click postion "+postion);

    }


}
