package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qgj.shoping.adapter.ShoppingAdapter;
import com.qgj.shoping.databinding.CellBinding;
import com.qgj.shoping.databinding.FragmentShoppingBinding;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.model.OrderModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;


public class ShoppingFragment extends Fragment {

    FragmentShoppingBinding binding;
    CellBinding cellBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShoppingBinding.inflate(getLayoutInflater());
        cellBinding = CellBinding.inflate(getLayoutInflater());

        if (SizeUtils.UID != 0) {
            initShopping();
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initShopping() {
        new Thread(() -> {
            try {
                OrderModel shopping = ServiceDaoImpl.getShoppingById(SizeUtils.UID);
                Log.e("initShopping/OrderModel", shopping.toString());
                if (shopping.getData().size() > 0) {
                    DataModel dataModel = ServiceDaoImpl.userInfoById(SizeUtils.UID);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            setData(shopping, dataModel);
                        });
                    }
                    return;
                }
                if (getActivity() != null) {
                    getActivity().runOnUiThread(this::initview);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setData(OrderModel shopping, DataModel dataModel) {
        binding.shopping.setAdapter(new ShoppingAdapter(shopping.getData(), dataModel, getContext(), cellBinding));
    }

    private void initview() {
        Toast.makeText(getContext(), "您的购物车是空的~~", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        //设置布局管理器
        binding.shopping.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}