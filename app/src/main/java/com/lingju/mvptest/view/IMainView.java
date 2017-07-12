package com.lingju.mvptest.view;

/**
 * Created by Ken on 2016/11/7.
 */
public interface IMainView {
    void setUserName(String name);

    String getUserName();

    void setPassWord(String pwd);

    String getPassWord();

    void showInfo(String info);

}
