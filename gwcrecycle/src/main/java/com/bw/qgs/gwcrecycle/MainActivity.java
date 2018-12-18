package com.bw.qgs.gwcrecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bw.qgs.gwcrecycle.adapter.OneAdapter;
import com.bw.qgs.gwcrecycle.bean.OneUser;
import com.bw.qgs.gwcrecycle.presenter.OnePresenter;
import com.bw.qgs.gwcrecycle.view.OneView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OneView {

    private RecyclerView recycle;
    private OnePresenter mOnePresenter;
    private List<OneUser.DataBean> mData;
    private OneAdapter mOneAdapter;
    private CheckBox allbox;
    private TextView total;

    private boolean flag = true;

    public List<OneUser.DataBean> getData() {
        return mData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mOnePresenter = new OnePresenter(this);
        mOnePresenter.getOne(UrlUtil.PATH);
    }

    private void initView() {
        recycle = (RecyclerView) findViewById(R.id.recycle);
        allbox = (CheckBox) findViewById(R.id.allbox);
        total = (TextView) findViewById(R.id.total);
    }

    /*public void setCheckAll(int s){
        int itemCount = mOneAdapter.getItemCount();
        for (int i = 0;i<itemCount;i++){
            *//*List<OneUser.DataBean.ListBean> list = mData.get(i).getList();
            for (int j = 0;j<list.size();j++){
                OneUser.DataBean.ListBean bean = list.get(j);
                bean.setSelected(s);
            }*//*

        }
        mOneAdapter.notifyDataSetChanged();
    }*/

    public void getTotal() {
        double ta = 0;
        List<OneUser.DataBean> data = mOneAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            List<OneUser.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                boolean checkbox = list.get(j).isCheckbox();
                if (checkbox) {
                    ta += list.get(j).getPrice() * list.get(j).getNum();
                }
            }
        }
        total.setText("合计：" + ta);
    }

    @Override
    public void onSuccess(final String result) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(linearLayoutManager);
        Gson gson = new Gson();
        final OneUser oneUser = gson.fromJson(result, OneUser.class);
        mData = oneUser.getData();
        mOneAdapter = new OneAdapter(getApplicationContext(), mData);
        recycle.setAdapter(mOneAdapter);
        allbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    for (int i = 0; i < mData.size(); i++) {
                        OneUser.DataBean dataBean = mData.get(i);
                        dataBean.setCheckbox(true);
                        List<OneUser.DataBean.ListBean> list = dataBean.getList();
                        for (int i1 = 0; i1 < list.size(); i1++) {
                            OneUser.DataBean.ListBean listBean = list.get(i1);
                            listBean.setCheckbox(true);
                        }
                    }
                    getTotal();
                    flag = false;
                    mOneAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < mData.size(); i++) {
                        OneUser.DataBean dataBean = mData.get(i);
                        List<OneUser.DataBean.ListBean> list = dataBean.getList();
                        dataBean.setCheckbox(false);
                        for (int i1 = 0; i1 < list.size(); i1++) {
                            OneUser.DataBean.ListBean listBean = list.get(i1);
                            listBean.setCheckbox(false);
                        }
                    }
                    getTotal();
                    flag = true;
                    mOneAdapter.notifyDataSetChanged();
                }
            }
        });

        mOneAdapter.setOnGroupClickListner(new OneAdapter.OnGroupClickListner() {
            @Override
            public void click(int gId, boolean is) {
                if (is) {
                    List<OneUser.DataBean> data = mOneAdapter.getData();
                    List<OneUser.DataBean.ListBean> list = data.get(gId).getList();
                    data.get(gId).setCheckbox(true);
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheckbox(true);
                    }
                    getTotal();
                    mOneAdapter.notifyDataSetChanged();
                } else {
                    List<OneUser.DataBean> data = mOneAdapter.getData();
                    data.get(gId).setCheckbox(false);
                    List<OneUser.DataBean.ListBean> list = data.get(gId).getList();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheckbox(false);
                    }
                    getTotal();
                    mOneAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onFailer(String msg) {

    }
}
