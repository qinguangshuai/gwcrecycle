package com.bw.qgs.gwcrecycle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bw.qgs.gwcrecycle.R;
import com.bw.qgs.gwcrecycle.bean.OneUser;

import java.util.List;

/**
 * date:2018/12/18    15:02
 * author:秦广帅(Lenovo)
 * fileName:OneAdapter
 */
public class OneAdapter extends RecyclerView.Adapter<OneAdapter.MyViewHolder> {

    private Context mContext;
    private List<OneUser.DataBean> mData;
    private OnGroupClickListner mOnGroupClickListner;
    public List<OneUser.DataBean> getData() {
        return mData;
    }

    public OneAdapter(Context context, List<OneUser.DataBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item1, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final OneUser.DataBean bean = mData.get(i);
        myViewHolder.onetext.setText(bean.getSellerName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        myViewHolder.onerecycle.setLayoutManager(linearLayoutManager);
        final TwoAdapter twoAdapter = new TwoAdapter(mContext,bean.getList());
        myViewHolder.onecheckbox.setChecked(bean.isCheckbox());
        myViewHolder.onecheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.onecheckbox.isChecked()){
                    mOnGroupClickListner.click(i,true);
                }else{
                    mOnGroupClickListner.click(i,false);
                }
            }
        });
        myViewHolder.onerecycle.setAdapter(twoAdapter);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView onetext;
        RecyclerView onerecycle;
        CheckBox onecheckbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            onetext = itemView.findViewById(R.id.onetext);
            onerecycle = itemView.findViewById(R.id.onerecycle);
            onecheckbox = itemView.findViewById(R.id.onecheckbox);
        }
    }
    public void setOnGroupClickListner(OnGroupClickListner onGroupClickListner1){
        this.mOnGroupClickListner=onGroupClickListner1;
    }
    public interface OnGroupClickListner{
        void click(int gId,boolean is);
    }
}
