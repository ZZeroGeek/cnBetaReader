package org.zreo.cnbetareader.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.zreo.cnbetareader.Entitys.NewsEntity;
import org.zreo.cnbetareader.R;
import org.zreo.cnbetareader.Utils.MyImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guang on 2015/7/24. 实现视图与数据的绑定
 */

public class NewsTitleAdapter extends BaseAdapter{

    private int resourceId;
    private List<NewsEntity> listItem;
    private Context mContext;

    private MyImageLoader myImageLoader;
    private ImageLoader imageLoader;  //图片加载器对象
    private DisplayImageOptions options;  ////显示图片的配置

    /**构造函数*/
   public NewsTitleAdapter(Context context, int textViewResourcedId, List<NewsEntity> objects) {
        super();
        resourceId = textViewResourcedId;
        listItem = objects;
        mContext = context;
        myImageLoader = new MyImageLoader(mContext);
        imageLoader = myImageLoader.getImageLoader();
        options = myImageLoader.getDisplayImageOptions();

    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.newsTitle = (TextView) view.findViewById(R.id.news_title);
            viewHolder.newsContent = (TextView) view.findViewById(R.id.news_summary);
            viewHolder.publishTime = (TextView) view.findViewById(R.id.news_publish_time);
            viewHolder.titleImage = (ImageView) view.findViewById(R.id.news_picture);
            viewHolder.commentNumber = (TextView) view.findViewById(R.id.news_comment_number);
            viewHolder.readerNumber = (TextView) view.findViewById(R.id.news_reader_number);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();  //重新获取viewHolder
        }
        viewHolder.newsTitle.setText(listItem.get(position).getTitle());
        //格式化输出新闻简介
        String homeText = listItem.get(position).getHometext().replace("<p>", "").replace("<strong>", "")
                                                            .replace("</p>", "").replace("</strong>", "");
        viewHolder.newsContent.setText(homeText);
        viewHolder.publishTime.setText(listItem.get(position).getInputtime());
        //viewHolder.imageUrl = listItem.get(position).getThumb();  //获取图片地址
        imageLoader.displayImage(listItem.get(position).getThumb(), viewHolder.titleImage, options);
        viewHolder.commentNumber.setText(String.valueOf(listItem.get(position).getComments()));
        viewHolder.readerNumber.setText(String.valueOf(listItem.get(position).getCounter()));
        return view;
    }
    class ViewHolder {
        private TextView newsTitle;     //标题
        private TextView newsContent;   //简介
        private TextView publishTime;   //发表时间
        private ImageView titleImage;    //显示图片
        private TextView commentNumber; //评论数
        private TextView readerNumber;  //阅读数
        //private String imageUrl;      //图片地址
    }

}

