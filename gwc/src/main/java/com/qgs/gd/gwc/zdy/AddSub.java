package com.qgs.gd.gwc.zdy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qgs.gd.gwc.R;

/**
 * date:2018/11/21    19:14
 * author:秦广帅(Lenovo)
 * fileName:AddSub
 */
public class AddSub extends LinearLayout {

    private View mView;
    private TextView add,num,sub;
    private OnNumListener mOnNumListener;

    public void setOnNumListener(OnNumListener onNumListener) {
        mOnNumListener = onNumListener;
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
        mView = View.inflate(context, R.layout.zdy, this);
        sub = mView.findViewById(R.id.subtext);
        num = mView.findViewById(R.id.numtext);
        add = mView.findViewById(R.id.addtext);

        num.setText("1");
    }

    public interface OnNumListener{
        void onNumChange(View view,int curNum);
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
            Toast.makeText(getContext(),"111",Toast.LENGTH_SHORT).show();
        }
    }

    public void setCurrentCount(int number){
        num.setText(number+"");
        if(mOnNumListener!=null){
            mOnNumListener.onNumChange(this,number);
        }
    }

    public int getCount(){
        return Integer.parseInt(num.getText().toString());
    }
}
