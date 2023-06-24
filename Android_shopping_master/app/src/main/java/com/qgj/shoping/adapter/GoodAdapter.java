package com.qgj.shoping.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.shoping.R;
import com.qgj.shoping.databinding.ItemGoodBinding;
import com.qgj.shoping.network.model.GoodModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;

import java.util.List;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.ViewHolder> {
    private final NavController navController;
    List<GoodModel.DataDTO> datas;
    Context mContext;

    public GoodAdapter(List<GoodModel.DataDTO> datas, Context context, NavController navController) {
        this.datas = datas;
        this.navController = navController;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGoodBinding binding = ItemGoodBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodAdapter.ViewHolder holder, int position) {
        //数据元
        GoodModel.DataDTO data = datas.get(position);
        //内容
        holder.mBinding.content.setText(data.getInfo());
        holder.mBinding.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转 去
                //把 商品id 传过去
                Bundle bundle = new Bundle();
                bundle.putBoolean("isd", true);
                bundle.putInt("id", data.getGid());
                navController.navigate(R.id.infoFragment, bundle);
            }
        });

        //图片
        Glide.with(mContext).load(ServiceDaoImpl.URL + "/" + data.getImg()).into(holder.mBinding.img);
        holder.mBinding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转 去
                //把 商品id 传过去
                Bundle bundle = new Bundle();
                bundle.putBoolean("isd", true);
                bundle.putInt("id", data.getGid());
                navController.navigate(R.id.infoFragment, bundle);
            }
        });

        //价格
        holder.mBinding.price.setText("¥ " + data.getPrice());
        holder.mBinding.bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转 去
                //把 商品id 传过去
                Bundle bundle = new Bundle();
                bundle.putBoolean("isd", true);
                bundle.putInt("id", data.getGid());
                navController.navigate(R.id.infoFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemGoodBinding mBinding;

        public ViewHolder(ItemGoodBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

}
