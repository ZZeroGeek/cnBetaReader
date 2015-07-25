package org.zreo.cnbetareader.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.zreo.cnbetareader.Activitys.Information_ThemeActivity;
import org.zreo.cnbetareader.R;
/**
 * Created by guang on 2015/7/23.
 */
public class DrawerLayoutFragment extends Fragment implements View.OnClickListener {
    /*
    * 七个按钮
     */
    private RelativeLayout mBtnInformation;
    private RelativeLayout mBtnComment;
    private RelativeLayout mBtnHot;
    private RelativeLayout mBtnFavorites;
    private RelativeLayout mBtnTopic;
    private RelativeLayout mBtnWeb;
    private RelativeLayout mBtnSetting;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        /*
        获取布局
         */
        //view = inflater.inflate(R.layout.fragment_drawer_layout, null);
        view = inflater.inflate(R.layout.fragment_drawer_layout, container, false);
        initViews(); //初始化按钮
        return view;
    }

    /*
    * 初始化按钮
     */
    public void initViews() {

        mBtnInformation = (RelativeLayout) view.findViewById(R.id.btn_information);
        mBtnComment = (RelativeLayout) view.findViewById(R.id.btn_comment);
        mBtnHot = (RelativeLayout) view.findViewById(R.id.btn_hot);
        mBtnFavorites = (RelativeLayout) view.findViewById(R.id.btn_favorites);
        mBtnTopic = (RelativeLayout) view.findViewById(R.id.btn_topic);
        mBtnWeb = (RelativeLayout) view.findViewById(R.id.btn_web);
        mBtnSetting = (RelativeLayout) view.findViewById(R.id.btn_setting);
        mBtnInformation.setOnClickListener(this);
        mBtnComment.setOnClickListener(this);
        mBtnHot.setOnClickListener(this);
        mBtnFavorites.setOnClickListener(this);
        mBtnTopic.setOnClickListener(this);
        mBtnWeb.setOnClickListener(this);
        mBtnSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_information:
                Toast.makeText(getActivity(), "最新资讯", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_comment:
                Toast.makeText(getActivity(), "精彩评论", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_hot:
                Toast.makeText(getActivity(), "本月Top10", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_favorites:
                Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_topic:
                Toast.makeText(getActivity(), "资讯主题", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), Information_ThemeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_web:
                mBtnWebAction();
                break;
            case R.id.btn_setting:
                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /*
    * 手机版网站按钮操作
    */
    public void mBtnWebAction() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("是否调用浏览器打开 cnBeta.com手机版网站？");   //设置要显示的内容
        alert.setCancelable(true);  //为真时可以通过返回键取消
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://m.cnbeta.com"));
                startActivity(intent);
            }
        });
        alert.setNegativeButton("取消", null);
        alert.show();   //显示对话框
    }
}
