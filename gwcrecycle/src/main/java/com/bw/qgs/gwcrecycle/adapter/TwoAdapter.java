package com.bw.qgs.gwcrecycle.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.qgs.gwcrecycle.R;
import com.bw.qgs.gwcrecycle.bean.OneUser;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/18    15:20
 * author:秦广帅(Lenovo)
 * fileName:TwoAdapter
 */
public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.MyViewHolder> {

    private Context mContext;
    private List<OneUser.DataBean.ListBean> list;

    public TwoAdapter(Context context, List<OneUser.DataBean.ListBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item2, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        OneUser.DataBean.ListBean bean = list.get(0);
        myViewHolder.twotext.setText(bean.getTitle());
        String images = bean.getImages();
        String[] str = images.split("!");
        Uri uri = Uri.parse(str[0]);
        myViewHolder.twoimage.setImageURI(uri);
        myViewHolder.twocheckbox.setChecked(bean.isCheckbox());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView twoimage;
        TextView twotext;
        CheckBox twocheckbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            twoimage = itemView.findViewById(R.id.twoimage);
            twotext = itemView.findViewById(R.id.twotext);
            twocheckbox = itemView.findViewById(R.id.twocheckbox);
        }
    }
}
