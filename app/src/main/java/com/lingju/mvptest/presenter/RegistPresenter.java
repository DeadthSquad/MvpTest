package com.lingju.mvptest.presenter;

import android.content.Context;

import com.lingju.mvptest.model.dao.UserDaoImpl;
import com.lingju.mvptest.view.IRegistView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import de.greenrobot.dao.UserBean;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Ken on 2016/11/8.
 */
public class RegistPresenter {

    private IRegistView mRegist;
    private UserDaoImpl mUserDao;

    public RegistPresenter(Context context, IRegistView view){
        this.mRegist = view;
        mUserDao = new UserDaoImpl(context);
    }

    public void getData(){
        Observable.timer(2000, TimeUnit.MILLISECONDS)       //2秒后开始订阅，模拟耗时加载数据
        .create(new Observable.OnSubscribe<List<UserBean>>() {
            /** 在此处调用订阅者的回调方法 **/
            @Override
            public void call(Subscriber<? super List<UserBean>> subscriber) {
                //获取数据库数据集
                List<UserBean> datas = mUserDao.findAll();
                subscriber.onNext(datas);
            }
        })
        .subscribeOn(Schedulers.io())   //在io线程中订阅
        .observeOn(AndroidSchedulers.mainThread())      //在主线程中响应回调
        .subscribe(new Action1<List<UserBean>>() {
            @Override
            public void call(List<UserBean> userBeen) {
                /* 在主线程刷新视图 */
                mRegist.updateList(userBeen);
            }
        });
    }
}
