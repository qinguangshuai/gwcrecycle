package com.qgs.gd.gouwuche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qgs.gd.gouwuche.adapter.OneAdapter;
import com.qgs.gd.gouwuche.bean.User;
import com.qgs.gd.gouwuche.presenter.OnePresenter;
import com.qgs.gd.gouwuche.url.UrlUtil;
import com.qgs.gd.gouwuche.view.OneView;
import com.qgs.gd.gouwuche.zidy.AddSub;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OneView {

    private Button mViewById;
    private ExpandableListView exlistview;
    private CheckBox all;
    private OnePresenter mPresenter;
    private OneAdapter adapter;
    private TextView heji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewById = findViewById(R.id.jiesuan);
        heji = findViewById(R.id.heji);
        exlistview = findViewById(R.id.exlistview);
        //设置取消箭头
        exlistview.setGroupIndicator(null);
        all = findViewById(R.id.all);
        mPresenter = new OnePresenter(this);
        mPresenter.jie(UrlUtil.PATH);
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

    private void setCheckAll(int s){
        int groupCount = adapter.getGroupCount();
        for (int i = 0;i<groupCount;i++){
            User.DataBean item = (User.DataBean) adapter.getGroup(i);
            List<User.DataBean.ListBean> itemList = item.getList();
            for (int j = 0;j<itemList.size();j++){
                User.DataBean.ListBean listBean = itemList.get(j);
                listBean.setSelected(s);
            }
        }

        adapter.notifyDataSetChanged();
    }

    public void getTotal(){
        double total = 0;
        int groupCount = adapter.getGroupCount();
        for (int i = 0;i<groupCount;i++){
            User.DataBean item = (User.DataBean) adapter.getGroup(i);
            List<User.DataBean.ListBean> list = item.getList();
            for (int j = 0;j<list.size();j++){
                User.DataBean.ListBean listBean = list.get(j);
                boolean checked = listBean.isChecked();
                if(checked){
                    String price = listBean.getPrice();
                    double v = Double.parseDouble(price);
                    total+=v*listBean.getNum();
                }
            }
        }
        heji.setText("合计:"+total);
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onFailer(String msg) {

    }

    @Override
    public void onSetData(final List<User.DataBean> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new OneAdapter(getApplicationContext(), list);
                exlistview.setAdapter(adapter);
                //默认全部展开
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    exlistview.expandGroup(i);
                }
                initShopCartChange();
            }
        });
    }
    private void initShopCartChange() {
        exlistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                User.DataBean group = (User.DataBean) adapter.getGroup(groupPosition);
                group.setChecked(!group.isChecked());
                int c = 0;
                if (group.isChecked()) {
                    c = 1;
                }
                List<User.DataBean.ListBean> list = group.getList();
                for (int i = 0; i < list.size(); i++) {
                    User.DataBean.ListBean bean = list.get(i);
                    bean.setSelected(c);
                }
                getTotal();
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        exlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                User.DataBean.ListBean listBean = (User.DataBean.ListBean) adapter.getChild(groupPosition,childPosition);
                boolean checked = listBean.isChecked();
                if(checked){
                    listBean.setSelected(0);
                }else{
                    listBean.setSelected(1);
                }
                adapter.notifyDataSetChanged();
                getTotal();
                return true;
            }
        });

        adapter.setOnNumChangedListener(new AddSub.OnNumChangedListener() {
            @Override
            public void onNumChange(View view, int curNum) {
                getTotal();
            }
        });
    }
}
