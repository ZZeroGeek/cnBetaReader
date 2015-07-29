package org.zreo.cnbetareader.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.zreo.cnbetareader.Activitys.NewsActivity;
import org.zreo.cnbetareader.Adapters.NewsTitleAdapter;
import org.zreo.cnbetareader.Model.News;
import org.zreo.cnbetareader.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guang on 2015/7/23.
 *显示新闻列表 *
 */
public class NewsTitleFragment extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener{

    View view;  //当前布局
    private ListView lv;
    /**定义一个动态数组，保存新闻信息*/
    private List<News> listItem = new ArrayList<News>();
    NewsTitleAdapter mAdapter;

    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数
    private View loadMoreView;    //加载更多布局

    SwipeRefreshLayout swipeLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_news_title, container, false);
        setHasOptionsMenu(true); //在fragment中使用menu菜单
        initListItem(); //初始化新闻列表
        initView();  //初始化布局
        return  view;
    }

    /**
     *  初始化布局
     */
    private void initView() {
        /**显示新闻标题的ListView*/
        lv = (ListView) view.findViewById(R.id.news_title_list_view);
        /**为ListView创建自定义适配器*/
        mAdapter = new NewsTitleAdapter(getActivity(), R.layout.news_title_item, listItem);
        /**为ListView绑定Adapter*/
        lv.setAdapter(mAdapter);
        /**为ListView添加点击事件*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "你点击了 " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);

            }
        });
        lv.setOnScrollListener(this);     //添加滑动监听
        loadMoreView = getActivity().getLayoutInflater().inflate(R.layout.load_more, null);
        lv.addFooterView(loadMoreView);   //设置列表底部视图
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        //设置刷新时动画的颜色，可以设置4个
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * 初始化新闻列表
     */
    public void initListItem(){
        String title = "Windows 10应用商店中国定制版现身 系统界面曝光";
        String summary = "7月24日消息，昨日有网友在国内某知名论坛发布疑似Win10应用商店中国定制版的系统界面图片，" +
                "一时间引发诸多热议。这名网友发帖称是从内部人士手里拿到了Win10特别版的系统映像，安装后发现这竟然" +
                "是Win10针对中国地区的定制版本。系统中除内置了很多微软旗下的服务外，还有一些本地化的功能。" +
                "据此他猜测，这极有可能就是专门提供给中国盗版用户免费使用的定制版本。";
        /**为动态数组添加数据*/
        for(int i = 0; i < 30; i++){
            News news = new News();
            news.setNewsTitle(i + "  " + title);
            news.setNewsContent(summary);
            news.setPublishTime("2015-07-24 10:30:38");
            news.setImageId(R.mipmap.news_picture);
            news.setCommentNumber(i * 20);
            news.setReaderNumber(i * 100);
            listItem.add(news);
        }
    }

    public void loadData(){
        String title = "Windows 10应用商店中国定制版现身 系统界面曝光";
        String summary = "7月24日消息，昨日有网友在国内某知名论坛发布疑似Win10应用商店中国定制版的系统界面图片，" +
                "一时间引发诸多热议。这名网友发帖称是从内部人士手里拿到了Win10特别版的系统映像，安装后发现这竟然" +
                "是Win10针对中国地区的定制版本。系统中除内置了很多微软旗下的服务外，还有一些本地化的功能。" +
                "据此他猜测，这极有可能就是专门提供给中国盗版用户免费使用的定制版本。";
        /**为动态数组添加数据*/
        int currentItem = listItem.size();
        for(int i = currentItem; i < currentItem + 10; i++){
            News news = new News();
            news.setNewsTitle(i + "  " + title);
            news.setNewsContent(summary);
            news.setPublishTime("2015-07-24 10:30:38");
            news.setImageId(R.mipmap.news_picture);
            news.setCommentNumber(i * 20);
            news.setReaderNumber(i * 100);
            listItem.add(news);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = listItem.size() - 1;    //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            //如果是自动加载,可以在这里放置异步加载数据的代码
            //Toast.makeText(this, "滑到底部", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    loadData();
                    mAdapter.notifyDataSetChanged(); //数据集变化后,通知adapter
                    ((TextView) loadMoreView.findViewById(R.id.load_more)).setText("加载中...");
                }
            }, 2000); //模拟加载自动加载太快，所以模拟加载延时执行

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ((TextView) loadMoreView.findViewById(R.id.load_more)).setText("加载更多");
                }
            }, 1800); //模拟加载自动加载太快，所以模拟加载延时执行

        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    @Override
    public void onRefresh() {
        // 这里做联网请求，然后handler处理完成之后的事情
        // 比如说swipeLayout.setRefreshing(false) 可以将进度条隐藏
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
                if (freshNumber < 20) {
                    freshData();  //加载刷新数据
                    mAdapter.notifyDataSetChanged(); //数据集变化后,通知adapter
                }
                customToast();
            }


        }, 1000);
    }

    /**
     * 自定义Toast，用于下拉刷新后的提示
     */
    private void customToast() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);  //获取屏幕分辨率
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(dm.widthPixels, dm.heightPixels/15);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View toastView = inflater.inflate(R.layout.toast, null);  // 取得xml里定义的view
        TextView tv = (TextView) toastView.findViewById(R.id.toast_text); // 取得xml里定义的TextView
        tv.setLayoutParams(params);  //设置Toast的宽度和高度
        if (freshNumber < 20) {
            tv.setText("新增" + addNumber+ "条资讯");
        }
        else {
            tv.setText("没有更多内容了");
            if(tv.getText().toString().equals("没有更多内容了")) {
                tv.setText("刚刚刷新过，等下再试吧");
            }
        }
        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    private int freshNumber = 0; //刷新显示的列表数
    private int addNumber; //每次刷新新增的资讯数量
    public void freshData(){
        String title = "Windows 10应用商店中国定制版现身 系统界面曝光";
        String summary = "7月24日消息，昨日有网友在国内某知名论坛发布疑似Win10应用商店中国定制版的系统界面图片，" +
                "一时间引发诸多热议。这名网友发帖称是从内部人士手里拿到了Win10特别版的系统映像，安装后发现这竟然" +
                "是Win10针对中国地区的定制版本。系统中除内置了很多微软旗下的服务外，还有一些本地化的功能。" +
                "据此他猜测，这极有可能就是专门提供给中国盗版用户免费使用的定制版本。";
        /**为动态数组添加数据*/
        addNumber = (int)(Math.random() * 10 + 1); //产生从1 - 10的随机数
        for(int i = freshNumber; i < freshNumber + addNumber; i++){
            News news = new News();
            news.setNewsTitle("刷新" + (i + 1) + "  " + title);
            news.setNewsContent(summary);
            news.setPublishTime("2015-07-24 10:30:38");
            news.setImageId(R.mipmap.news_picture);
            news.setCommentNumber(i * 20);
            news.setReaderNumber(i * 100);
            listItem.add(0,news);
        }
        freshNumber = freshNumber + addNumber;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.offline_download) {
            Toast.makeText(getActivity(), "离线下载", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}