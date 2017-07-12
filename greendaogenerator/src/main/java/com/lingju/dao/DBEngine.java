package com.lingju.dao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * 使用greendao框架所需的数据库生产者
 **/
public class DBEngine {

    public static void main(String args[]) throws Exception {
        /* 参数一：数据库版本号（用于升级数据库）；
            参数二：要生成的dao类的所在包路径 */
        Schema schema = new Schema(1, "de.greenrobot.dao");
        addTabel(schema);
        /* 参数二：生成dao类的所在包所在的路径 */
        new DaoGenerator().generateAll(schema, "E:/WorkSpace/MvpTest/app/src/main/java");
    }

    /**
     * 创建某个实体对象对应的数据库表
     **/
    private static void addTabel(Schema schema) {
        /* 获取实体对象映射实例，默认表名为类名 */
        Entity user = schema.addEntity("UserBean");
        // 自定义表名
//        user.setTableName("CustomNote");
        /* 设置主键、自增长 */
        user.addIdProperty().primaryKey().autoincrement();
        /* 设置表相关字段。 参数：列名 <--> 实体类字段 */
        user.addStringProperty("username");
        user.addStringProperty("password");
        // 可以使用此方法定义实体类的属性名和数据库的列名不同，如下实体类名为 sex，列名为_SEX
//        user.addStringProperty("username").columnName("name");

    }
}
