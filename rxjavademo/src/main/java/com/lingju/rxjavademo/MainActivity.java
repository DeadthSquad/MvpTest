package com.lingju.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.just("Curry", "Carter", "Cousins","Kobe")
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.startsWith("C");
                    }
                })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return 30;
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
                    public void onNext(Integer s) {
                        Log.i("LingJu", "订阅结果：" + s);
                    }
                });
    }
}
