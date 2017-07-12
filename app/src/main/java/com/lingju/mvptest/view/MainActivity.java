package com.lingju.mvptest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lingju.mvptest.R;
import com.lingju.mvptest.presenter.MainPresenter;

import de.greenrobot.dao.UserBean;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener {

    private EditText mEtUserName;
    private EditText mEtPwd;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mMainPresenter = new MainPresenter(getApplicationContext(), this);
    }

    private void initView() {
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_regist).setOnClickListener(this);
        findViewById(R.id.btn_load).setOnClickListener(this);
    }

    @Override
    public void setUserName(String name) {
        mEtUserName.setText(name);
    }

    @Override
    public String getUserName() {
        return mEtUserName.getText().toString();
    }

    @Override
    public void setPassWord(String pwd) {
        mEtPwd.setText(pwd);
    }

    @Override
    public String getPassWord() {
        return mEtPwd.getText().toString();
    }

    @Override
    public void showInfo(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mMainPresenter.login();
                break;
            case R.id.btn_regist:
                mMainPresenter.regist(this);
                break;
            case R.id.btn_load:
                mMainPresenter.load();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            mMainPresenter.reSet((UserBean) data.getSerializableExtra(RegisterActivity.USER));
        }
    }
}
