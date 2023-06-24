package com.qgj.shoping.network.service;

import com.qgj.shoping.network.model.User;
import com.qgj.shoping.network.model.AdminBuyModel;
import com.qgj.shoping.network.model.AllModel;
import com.qgj.shoping.network.model.BuyModel;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.model.GoodInfoModel;
import com.qgj.shoping.network.model.GoodModel;
import com.qgj.shoping.network.model.OrderModel;
import com.qgj.shoping.network.model.RegModel;
import com.qgj.shoping.network.model.ShoppingModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceDaoImpl {
    private static ServiceDao serviceDao;
    //public static String URL ="http://10.0.2.2:8989";
    public static String URL = "http://192.168.31.197:8989";

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)//本地服务器直接 10.0.2.2 我的手机192.168.43.222
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceDao = retrofit.create(ServiceDao.class);
    }

    //登录结果
    public static DataModel login(User user) throws IOException {
        return Call(serviceDao.login(user));
    }

    //查询用户信息
    public static DataModel userInfoById(int id) throws IOException {
        return Call(serviceDao.userInfoById(id));
    }

    //注册接口
    public static RegModel register(User user) throws IOException {
        return Call(serviceDao.register(user));
    }

    //更新密码
    public static DataModel updateuserInfo(User user) throws IOException {
        return Call(serviceDao.updateuserInfo(user));
    }

    //购物
    public static GoodModel getGoodAll(String info) throws IOException {
        return Call(serviceDao.getGoodAll(info));
    }

    //购物
    public static GoodInfoModel getGoodById(int id) throws IOException {
        return Call(serviceDao.getGoodById(id));
    }

    public static ShoppingModel addShopping(AdminBuyModel adminBuyModel) throws IOException {
        return Call(serviceDao.addShopping(adminBuyModel));
    }

    //查看客户的购物车
    public static OrderModel getShoppingById(int id) throws IOException {
        return Call(serviceDao.getShoppingById(id));
    }

    public static ShoppingModel deleteShoppingById(int id) throws IOException {
        return Call(serviceDao.deleteShoppingById(id));
    }

    public static ShoppingModel addOrder(BuyModel buyModel) throws IOException {
        return Call(serviceDao.addOrder(buyModel));
    }

    public static AllModel getAll(int id) throws IOException {
        return Call(serviceDao.getAll(id));
    }

    private static <T> T Call(Call<T> call) throws IOException {
        return call.execute().body();
    }

    public static ShoppingModel orderEvaluate(BuyModel buyModel) throws IOException {
        return Call(serviceDao.orderEvaluate(buyModel));
    }

}
