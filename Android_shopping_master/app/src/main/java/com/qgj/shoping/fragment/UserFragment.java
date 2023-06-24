package com.qgj.shoping.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgj.shoping.R;
import com.qgj.shoping.databinding.FragmentUserBinding;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;


public class UserFragment extends Fragment {
    FragmentUserBinding binding;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBth();
        if (SizeUtils.UID != 0) {
            initUser(SizeUtils.UID);
            intBut();
        }
    }

    private void intBut() {
        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.orderFragment, new Bundle());
            }
        });
        binding.updatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.updatePasswordFragment, new Bundle());
            }
        });

        binding.updateuserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.updateuserinfoFragment, new Bundle());
            }
        });

    }

    private void initUser(int id) {
        new Thread(() -> {
            try {
                DataModel dataModel = ServiceDaoImpl.userInfoById(id);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        binding.login.setText(dataModel.getData().getUsername());
                        binding.login.setEnabled(false);
                        binding.unlogin.setVisibility(View.VISIBLE);
                        binding.unlogin.setText("退出登录");
                        binding.unlogin.setEnabled(true);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void initBth() {
        //去登录页面
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("login", true);
                navController.navigate(R.id.loginFragment, bundle);
            }
        });
        //退出登录
        binding.unlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SizeUtils.UID = 0; //退出登录
                binding.login.setText("点击登录");
                binding.login.setEnabled(true);
                binding.unlogin.setVisibility(View.INVISIBLE);
                binding.unlogin.setText("还未登录");
                binding.unlogin.setEnabled(true);
                Bundle bundle = new Bundle();
                bundle.putBoolean("login", true);
                navController.navigate(R.id.loginFragment, bundle);
            }
        });
    }
}