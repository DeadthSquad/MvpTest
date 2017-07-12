package com.lingju.mvptest.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.lingju.mvptest.model.dao.UserDaoImpl;
import com.lingju.mvptest.view.IMainView;
import com.lingju.mvptest.view.RegisterActivity;

import de.greenrobot.dao.UserBean;

/**
 * Created by Ken on 2016/11/7.
 */
public class MainPresenter {

    private IMainView mMainView;
    private UserDaoImpl mUserDao;

    public MainPresenter(Context context, IMainView mainView) {
        this.mMainView = mainView;
        this.mUserDao = new UserDaoImpl(context);
    }

    /** 登录 **/
    public void login() {
        String status;
        String userName = mMainView.getUserName();
        String passWord = mMainView.getPassWord();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
            status = "用户名或密码不能为空！";
        } else {
            UserBean user = new UserBean(null, userName, passWord);
            if (mUserDao.checkUser(user)) {
                status = "登录成功！！";
            } else {
                status = "用户名或密码错误！";
            }
        }
        mMainView.showInfo(status);
    }

    public void load() {
        UserBean user = mUserDao.loadUser();
        if(user != null) {
            mMainView.setUserName(user.getUsername());
            mMainView.setPassWord(user.getPassword());
        }else {
            mMainView.showInfo("您没有保存的账号");
        }
    }

    public void reSet(UserBean user){
        mMainView.setUserName(user.getUsername());
        mMainView.setPassWord(user.getPassword());
    }

    /** 注册 **/
    public void regist(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, 0);
    }
}
