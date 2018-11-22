package com.qgs.gd.gwc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qgs.gd.gwc.adapter.OneAdapter;
import com.qgs.gd.gwc.bean.User;
import com.qgs.gd.gwc.presenter.OnePresenter;
import com.qgs.gd.gwc.url.UrlUtil;
import com.qgs.gd.gwc.view.OneView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OneView {

    private OnePresenter mOnePresenter;
    private ExpandableListView exlistview;
    private List<User.DataBean> list;
    private OneAdapter oneAdapter;
    private CheckBox all;
    private TextView hj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exlistview = findViewById(R.id.exlistview);
        all = findViewById(R.id.all);
        hj = findViewById(R.id.hj);
        exlistview.setGroupIndicator(null);
        mOnePresenter = new OnePresenter(this);
        mOnePresenter.onePresenter(UrlUtil.PATH);
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setCheckAll(1);
                    getTotal();
                }else{
                    setCheckAll(0);
                    getTotal();
                }
            }
        });
    }

    public void getTotal(){
        double total = 0;
        int groupCount = oneAdapter.getGroupCount();
        for(int i =0;i<groupCount;i++){
            User.DataBean group = (User.DataBean) oneAdapter.getGroup(i);
            List<User.DataBean.ListBean> list = group.getList();
            for (int j = 0;j<list.size();j++){
                User.DataBean.ListBean bean = list.get(j);
                boolean checked = bean.isChecked();
                if(checked){
                    double price = bean.getPrice();
                    total+=price*bean.getNum();
                }
            }
        }
        hj.setText("合计:"+total);
    }

    public void setCheckAll(int s){
        int groupCount = oneAdapter.getGroupCount();
        for(int i = 0;i<groupCount;i++){
            User.DataBean item = (User.DataBean) oneAdapter.getGroup(i);
            List<User.DataBean.ListBean> itemList = item.getList();
            for (int j = 0;j<itemList.size();j++){
                User.DataBean.ListBean listBean = itemList.get(j);
                listBean.setSelected(s);
            }
        }
        oneAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        User user = gson.fromJson(result, User.class);
        list = user.getData();
        oneAdapter = new OneAdapter(getApplicationContext(), list);
        exlistview.setAdapter(oneAdapter);
        for (int i = 0; i< oneAdapter.getGroupCount(); i++){
            exlistview.expandGroup(i);
        }
        initShopCartChange();
    }

    @Override
    public void onFailer(String msg) {

    }

    private void initShopCartChange(){
        exlistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                User.DataBean group = (User.DataBean) oneAdapter.getGroup(groupPosition);
                group.setChecked(!group.isChecked());
                int c = 0;
                if(group.isChecked()){
                    c=1;
                }
                List<User.DataBean.ListBean> list = group.getList();
                for (int i =0;i<list.size();i++){
                    User.DataBean.ListBean bean = list.get(i);
                    bean.setSelected(c);
                }
                getTotal();
                oneAdapter.notifyDataSetChanged();
                return true;
            }
        });

        exlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                User.DataBean.ListBean child = (User.DataBean.ListBean) oneAdapter.getChild(groupPosition,childPosition);
                boolean checked = child.isChecked();
                if(checked){
                    child.setSelected(0);
                }else{
                    child.setSelected(1);
                }
                getTotal();
                oneAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
