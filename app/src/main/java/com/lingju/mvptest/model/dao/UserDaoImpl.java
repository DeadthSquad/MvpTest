package com.lingju.mvptest.model.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import de.greenrobot.dao.DaoMaster;
import de.greenrobot.dao.DaoSession;
import de.greenrobot.dao.UserBean;
import de.greenrobot.dao.UserBeanDao;

/**
 * Created by Ken on 2016/11/7.
 */
public class UserDaoImpl {

    private DaoMaster.DevOpenHelper mOpenHelper;
    private final SQLiteDatabase mDb;
    private final UserBeanDao mUserDao;

    public UserDaoImpl(Context context) {
        /** 参数1：上下文对象  参数2：数据库名称 **/
        mOpenHelper = new DaoMaster.DevOpenHelper(context, "test.db", null);
        mDb = mOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(mDb);
        DaoSession daoSession = daoMaster.newSession();
        mUserDao = daoSession.getUserBeanDao();
    }

    private boolean query(UserBean user) {
        /** Google API写法 **/
        Cursor cursor = mDb.query(UserBeanDao.TABLENAME, null, UserBeanDao.Properties.Username.columnName + "=? and " +
                        UserBeanDao.Properties.Password.columnName + "=?", new String[]{user.getUsername(), user.getPassword()}
                , null, null, null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;

    }

    /**
     * 插入数据
     **/
    public boolean insert(UserBean user) {
        /* 插入一条数据 */
        return mUserDao.insert(user) > 0;
    }

    /**
     * 批量插入
     **/
    public void insertGroup(List<UserBean> datas) {
        mUserDao.insertInTx(datas);
    }

    /**
     * 删除数据
     **/
    public void delete(UserBean user) {
        mUserDao.delete(user);
    }

    /**
     * 修改数据
     **/
    public void update(UserBean user) {
        mUserDao.update(user);
    }

    /**
     * 查询数据单条
     **/
    private UserBean get() {
        /** GreenDao API 写法；通过构建 QueryBuilder 来实现查询功能 **/
        return mUserDao.queryBuilder().where(UserBeanDao.Properties.Id.eq(1)).unique();
    }

    /**
     * 查询所有数据
     **/
    public List<UserBean> findAll() {
        return mUserDao.queryBuilder().list();
    }


    /**
     * 登录验证
     **/
    public boolean checkUser(UserBean user) {
        return query(user);
    }

    /**
     * 加载存在用户
     **/
    public UserBean loadUser() {
        return get();
    }
}
