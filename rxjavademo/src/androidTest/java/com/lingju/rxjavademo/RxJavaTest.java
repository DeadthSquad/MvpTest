package com.lingju.rxjavademo;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by Ken on 2016/11/8.
 */
public class RxJavaTest extends AndroidTestCase {

    public void testCreate() throws Exception {
        Observable.interval(3000, TimeUnit.MILLISECONDS)
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(1);
                    }
                })
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("LingJu", "订阅结果：" + integer);
                    }
                });
    }
}
