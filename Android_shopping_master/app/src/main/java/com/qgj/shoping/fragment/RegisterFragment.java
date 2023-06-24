package com.qgj.shoping.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qgj.shoping.R;
import com.qgj.shoping.databinding.FragmentRegisterBinding;
import com.qgj.shoping.network.model.User;
import com.qgj.shoping.network.model.RegModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;

import java.io.IOException;


public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBth();
    }

    private void initBth() {
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                跳转登录
                String username = binding.username.getText().toString();
                String password = binding.pasword.getText().toString();
                String password2 = binding.pasword2.getText().toString();
                if (username.equals("") || password2.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "请输入用户名或者密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断输入的密码是否相同
                if (!password2.equals(password)) {
                    Toast.makeText(getActivity(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                //包装数据然后去登录
                //封装数据
                User user = new User(username, password);
                //发送请求咯 哈哈哈 加油 啊 我可以的 慢慢写
                new Thread(() -> {
                    try {
                        RegModel regModel = ServiceDaoImpl.register(user);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(() -> {
                                if (regModel.getCode() != 400) {
                                    //请求成功 之后
                                    Bundle bundle = new Bundle();
                                    bundle.putBoolean("login", true);
                                    navController.navigate(R.id.loginFragment, bundle);
                                }
                                Toast.makeText(getContext(), regModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        });
    }
}