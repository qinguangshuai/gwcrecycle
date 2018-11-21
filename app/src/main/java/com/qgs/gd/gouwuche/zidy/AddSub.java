package com.qgs.gd.gouwuche.zidy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qgs.gd.gouwuche.R;

/**
 * date:2018/11/21    9:46
 * author:秦广帅(Lenovo)
 * fileName:AddSub
 */
public class AddSub extends LinearLayout {

    private View mView;
    private TextView add,num,sub;
    private OnNumChangedListener mOnNumChangedListener;

    public void setOnNumChangedListener(OnNumChangedListener onNumChangedListener) {
        mOnNumChangedListener = onNumChangedListener;
    }

    public AddSub(Context context) {
        this(context,null);
    }

    public AddSub(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public AddSub(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener();
        getCount();
    }

    private void initListener() {
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jia();
            }
        });
        sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jian();
            }
        });
    }

    public void initView(Context context){
        mView = View.inflate(context, R.layout.addsub_zi, this);
        add = mView.findViewById(R.id.addtext);
        num = mView.findViewById(R.id.numtext);
        sub = mView.findViewById(R.id.subtext);

        num.setText("1");
    }

    public void jia(){
        String s = num.getText().toString();
        int anInt = Integer.parseInt(s);
        anInt++;
        setCurrentCount(anInt);
    }

    public void jian(){
        String s = num.getText().toString();
        int anInt = Integer.parseInt(s);
        if(anInt>1){
            anInt--;
            setCurrentCount(anInt);
        }else{
            Toast.makeText(getContext(),"不能再少了",Toast.LENGTH_SHORT).show();
        }
    }

    public int getCount(){
        return Integer.parseInt(num.getText().toString());
    }

    public void setCurrentCount(int number){
        num.setText(number+"");
        if(mOnNumChangedListener != null){
            mOnNumChangedListener.onNumChange(this,number);
        }
    }

    public interface OnNumChangedListener{
        void onNumChange(View view,int curNum);
    }
}
