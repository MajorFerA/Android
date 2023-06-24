package com.qgj.shoping.network.service;

import com.qgj.shoping.network.model.AdminBuyModel;
import com.qgj.shoping.network.model.AllModel;
import com.qgj.shoping.network.model.BuyModel;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.model.User;
import com.qgj.shoping.network.model.GoodInfoModel;
import com.qgj.shoping.network.model.GoodModel;
import com.qgj.shoping.network.model.OrderModel;
import com.qgj.shoping.network.model.RegModel;
import com.qgj.shoping.network.model.ShoppingModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceDao {
    @POST("/user/login")
    Call<DataModel> login(@Body User user);

    //查询用户
    @GET("/user/{id}")
    Call<DataModel> userInfoById(@Path("id") int id);

    @POST("/user/register")
    Call<RegModel> register(@Body User user);

    @POST("/user/update")
    Call<DataModel> updateuserInfo(@Body User user);

    @GET("/good")
    Call<GoodModel> getGoodAll(@Query("info") String info);

    @GET("/good/{id}")
    Call<GoodInfoModel> getGoodById(@Path("id") int id);

    //添加用户的购物车
    @POST("/shopping")
    Call<ShoppingModel> addShopping(@Body AdminBuyModel adminBuyModel);

    //删除订单
    @DELETE("/shopping")
    Call<ShoppingModel> deleteShoppingById(@Query("id") int id);

    //查看用户的购物车 哈啊哈
    @GET("/shopping/info/{id}")
    Call<OrderModel> getShoppingById(@Path("id") int id);

    @POST("/order")
    Call<ShoppingModel> addOrder(@Body BuyModel buyModel);

    //查询用户的订单
    @GET("/order/info/{id}")
    Call<AllModel> getAll(@Path("id") int id);

    //订单评价
    @POST("/order/evaluate")
    Call<ShoppingModel> orderEvaluate(@Body BuyModel buyModel);
}
