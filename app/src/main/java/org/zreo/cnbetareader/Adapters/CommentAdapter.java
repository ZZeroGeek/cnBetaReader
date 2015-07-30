package org.zreo.cnbetareader.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.zreo.cnbetareader.Entitys.CnComment;
import org.zreo.cnbetareader.R;

import java.util.List;

public class CommentAdapter extends BaseAdapter{

    Context _context;
    private int resourceId;
    private List<CnComment> commentItem;

    public  CommentAdapter(Context mContext,int textViewResourcedId, List<CnComment> objects) {
        // TODO Auto-generated constructor stub
        _context=mContext;
        resourceId = textViewResourcedId;
        commentItem=objects;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return commentItem.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        View view;
        final ViewHolder holder;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(_context);
            view = inflater.inflate(resourceId, null);
            holder = new ViewHolder();
            holder.imageView=(ImageView)view.findViewById(R.id.imageView1);
            holder.textView=(TextView)view.findViewById(R.id.user_name);
            holder.textView1 =(TextView)view.findViewById(R.id.support_against);
            holder.viewBtn=(ImageButton)view.findViewById(R.id.button1);
            holder.textView2 =(TextView)view.findViewById(R.id.comment_text);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder=(ViewHolder)view.getTag();
        }

        //ImageView imageView=(ImageView)view.findViewById(R.id.imageView1);
        //TextView textView = (TextView)view.findViewById(R.id.textView1);
        //imageView.setImageResource(_images[position]);
        //textView.setText(_texts[position]);
        holder.imageView.setImageResource(commentItem.get(position).getImageId());
        holder.textView2.setText(commentItem.get(position).getTestComment());
        holder.textView.setText(commentItem.get(position).getUserName());
        holder.textView1.setText(commentItem.get(position).getsupportAgainst());
        holder.viewBtn.setImageResource(commentItem.get(position).getCommentMenu());
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Toast.makeText(_context, "你点击了ImageButton", Toast.LENGTH_SHORT)
                            .show();
            }
        });
        return view;
    }
    public class ViewHolder{
        public ImageView imageView;
        public TextView textView1;
        public TextView textView;
        public ImageButton viewBtn;
        public TextView textView2;
    }

}
