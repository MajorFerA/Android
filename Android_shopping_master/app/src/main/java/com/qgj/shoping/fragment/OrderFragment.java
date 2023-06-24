package com.qgj.shoping.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.shoping.adapter.OrderAdapter;
import com.qgj.shoping.databinding.FragmentOrderBinding;
import com.qgj.shoping.network.model.AllModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;


public class OrderFragment extends Fragment {


    FragmentOrderBinding binding;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        initShopping();
    }

    private void initShopping() {
        new Thread(() -> {
            try {
                AllModel all = ServiceDaoImpl.getAll(SizeUtils.UID);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        //binding.rec.setAdapter(new UserOrderAdapter(all.getData(),getContext()));
                        binding.rec.setAdapter(new OrderAdapter(all.getData(), getContext(), navController));

                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}