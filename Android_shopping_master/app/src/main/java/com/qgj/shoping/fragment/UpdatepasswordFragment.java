package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qgj.shoping.databinding.FragmentUpdatepasswordBinding;
import com.qgj.shoping.network.model.User;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;


public class UpdatepasswordFragment extends Fragment {


    FragmentUpdatepasswordBinding binding;
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
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void UpdateuserInfo(User userInfo ) {
        new Thread(() -> {
            try {
                DataModel updateModel = ServiceDaoImpl.updateuserInfo(userInfo);

                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        if (updateModel.getCode() == 200) {
                            Toast.makeText(getContext(), "密码已修改！", Toast.LENGTH_SHORT).show();
                            navController.popBackStack();
                        }else{
                            Toast.makeText(getContext(), "密码修改失败！", Toast.LENGTH_SHORT).show();
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
        binding = FragmentUpdatepasswordBinding.inflate(getLayoutInflater());
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
                if (binding.oldpasword.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "请输入原密码！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.newpasword.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "请输入新密码！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.oldpasword.getText().toString().trim().equals(dataModel.getData().getPassword())) {
                    User userInfo = new User(dataModel.getData().getUsername(), binding.newpasword.getText().toString().trim());
                    userInfo.setId(SizeUtils.UID);
                    UpdateuserInfo(userInfo);
                } else {
                    Toast.makeText(getContext(), "原密码输入不正确！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
