package com.lingju.mvptest.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lingju.mvptest.R;
import com.lingju.mvptest.presenter.RegistPresenter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.UserBean;

/**
 * Created by Ken on 2016/11/8.
 */
public class RegisterActivity extends Activity implements IRegistView {

    public static final String USER = "user";
    private ListView mLvUsers;
    private UserAdapter mAdapter;
    private List<UserBean> userList = new ArrayList<>();
    private RegistPresenter mRegistPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        mLvUsers = (ListView) findViewById(R.id.lv_users);
        mAdapter = new UserAdapter();
        mLvUsers.setAdapter(mAdapter);
        mLvUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                show(position);
                return true;
            }
        });

        mRegistPresenter = new RegistPresenter(getApplicationContext(), this);
        mRegistPresenter.getData();
    }


    @Override
    public void updateList(List datas) {
        userList.clear();
        userList.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    public void show(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("新用户注册")
                .setMessage("您确定要注册成该用户")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(USER, (UserBean) mAdapter.getItem(position));
                        setResult(0, intent);
                        finish();
                    }
                }).show();
    }

    class UserAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userList.size();
        }

        @Override
        public Object getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            /* 加载视图 */
            if (convertView == null) {
                tv = new TextView(parent.getContext());
                tv.setTextColor(Color.CYAN);
                tv.setTextSize(18);
                tv.setPadding(8, 8, 8, 8);
                convertView = tv;
            } else {
                tv = (TextView) convertView;
            }

            /* 绑定数据 */
            UserBean user = userList.get(position);
            tv.setText(user.getId() + "." + user.getUsername());

            return convertView;
        }
    }
}
