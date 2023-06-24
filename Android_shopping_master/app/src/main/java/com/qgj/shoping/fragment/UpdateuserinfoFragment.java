package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qgj.shoping.databinding.FragmentUpdateuserinfoBinding;
import com.qgj.shoping.network.model.User;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;


public class UpdateuserinfoFragment extends Fragment {


    FragmentUpdateuserinfoBinding binding;
    NavController navController;
    DataModel dataModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initUser(int id) {
        new Thread(() -> {
            try {
                dataModel = ServiceDaoImpl.userInfoById(id);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        binding.username.setText(dataModel.getData().getUsername());
                        binding.phone.setText(dataModel.getData().getPhone());
                        binding.shippingaddr.setText(dataModel.getData().getShippingaddr());
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void UpdateuserInfo(User userInfo) {
        new Thread(() -> {
            try {
                DataModel updateModel = ServiceDaoImpl.updateuserInfo(userInfo);

                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        if (updateModel.getCode() == 200) {
                            Toast.makeText(getContext(), "个人信息已更新！", Toast.LENGTH_SHORT).show();
                            navController.popBackStack();
                        } else {
                            Toast.makeText(getContext(), "个人信息修改失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateuserinfoBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBth();
        if (SizeUtils.UID != 0) {
            initUser(SizeUtils.UID);
        }

    }

    private void initBth() {
        //去登录页面
        binding.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.phone.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "请输入手机号码！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.phone.getText().toString().trim().length() != 11) {
                    Toast.makeText(getContext(), "请输入11位手机号码！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.shippingaddr.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "请输入收货地址！", Toast.LENGTH_SHORT).show();
                    return;
                }

                User userInfo = new User(dataModel.getData().getUsername(), dataModel.getData().getPassword());
                userInfo.setId(SizeUtils.UID);
                userInfo.setPhone(binding.phone.getText().toString());
                userInfo.setShippingaddr(binding.shippingaddr.getText().toString());
                UpdateuserInfo(userInfo);
            }
        });
    }
}
