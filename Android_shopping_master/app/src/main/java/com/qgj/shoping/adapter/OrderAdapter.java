package com.qgj.shoping.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.shoping.R;

import com.qgj.shoping.databinding.ItemOrderBinding;
import com.qgj.shoping.network.model.AllModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<AllModel.DataDTO> datas;
    Context mContext;
    private final NavController navController;

    public OrderAdapter(List<AllModel.DataDTO> datas, Context context, NavController navController) {
        this.datas = datas;
        this.navController = navController;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemOrderBinding binding = ItemOrderBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //数据元
        AllModel.DataDTO data = datas.get(position);
        Log.e("TGA", data.toString());
        //内容
        holder.mBinding.info.setText("\u3000\u3000" + data.getGoodname());
        //图片
        Glide.with(mContext).load(ServiceDaoImpl.URL + "/" + data.getImg()).into(holder.mBinding.img);
        //价格
        holder.mBinding.price.setText("单价:¥" + data.getPrice());
        //数量
        holder.mBinding.num.setText("数量:" + data.getNum());
        holder.mBinding.total.setText("合计:" + data.getTotal());
        holder.mBinding.stt.setText(data.getStatus());
        holder.mBinding.time.setText(data.getTime());

        if (data.getOstate() != null && data.getOstate() == 1) {
            holder.mBinding.evaluate.setVisibility(View.GONE);
            holder.mBinding.evaluateDetail.setVisibility(View.VISIBLE);
        } else {
            holder.mBinding.evaluate.setVisibility(View.VISIBLE);
            holder.mBinding.evaluateDetail.setVisibility(View.GONE);
        }

        holder.mBinding.llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("img", data.getImg());
                bundle.putString("info", data.getGoodname());
                bundle.putDouble("price", data.getPrice());
                bundle.putInt("num", data.getNum());
                bundle.putDouble("total", data.getTotal());

                bundle.putString("time", data.getTime());
                bundle.putString("receiver", data.getReceiver());
                bundle.putString("phone", data.getPhone());
                bundle.putString("shippingaddr", data.getShippingaddr());

                navController.navigate(R.id.orderdetailFragment, bundle);
            }
        });

        holder.mBinding.evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", data.getId());
                bundle.putString("img", data.getImg());
                bundle.putString("info", data.getGoodname());
                bundle.putDouble("price", data.getPrice());
                bundle.putInt("num", data.getNum());
                bundle.putDouble("total", data.getTotal());

                bundle.putString("time", data.getTime());
                bundle.putString("phone", data.getPhone());
                bundle.putString("shippingaddr", data.getShippingaddr());

                navController.navigate(R.id.mycommentFragment, bundle);
            }
        });

        holder.mBinding.evaluateDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", data.getId());
                bundle.putString("img", data.getImg());
                bundle.putString("info", data.getGoodname());
                bundle.putDouble("price", data.getPrice());
                bundle.putInt("num", data.getNum());
                bundle.putDouble("total", data.getTotal());

                bundle.putString("time", data.getTime());
                bundle.putString("phone", data.getPhone());
                bundle.putString("shippingaddr", data.getShippingaddr());

                bundle.putInt("ostate", data.getOstate());
                bundle.putInt("score", data.getScore());
                bundle.putString("evaluation", data.getEvaluation());
                bundle.putString("evaluatetime", data.getEvaluatetime());

                navController.navigate(R.id.mycommentFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding mBinding;

        public ViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
