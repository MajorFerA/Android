package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.qgj.shoping.databinding.FragmentOrderdetailBinding;
import com.qgj.shoping.network.service.ServiceDaoImpl;


public class OrderdetailFragment extends Fragment {


    FragmentOrderdetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderdetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {

        if (getArguments() != null) {

            Glide.with(this).load(ServiceDaoImpl.URL + "/" + getArguments().getString("img")).into(binding.img);
            binding.info.setText("\u3000\u3000" + getArguments().getString("info"));

            binding.price.setText("单价: ¥" + getArguments().getDouble("price"));
            binding.num.setText("数量:" + getArguments().getInt("num"));
            binding.total.setText("合计:" + getArguments().getDouble("total"));

            binding.paytime.setText("支付时间:" + getArguments().getString("time"));

            binding.username.setText("收货人:" + getArguments().getString("receiver"));
            binding.phone.setText("手机号码:" + getArguments().getString("phone"));
            binding.shippingaddr.setText("收货地址:" + getArguments().getString("shippingaddr"));
        }
    }
}