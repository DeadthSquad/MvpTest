package com.lingju.mvptest;

import android.test.AndroidTestCase;

import com.lingju.mvptest.model.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.UserBean;


/**
 * Created by Ken on 2016/11/8.
 */
public class UserTest extends AndroidTestCase {

    public void testInsert() throws Exception{
        UserDaoImpl dao = new UserDaoImpl(getContext());
        List<UserBean> datas = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            datas.add(new UserBean(null, "库里"+i, "123"));
        }
        dao.insertGroup(datas);
    }
}
