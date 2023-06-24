package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.qgj.shoping.R;
import com.qgj.shoping.databinding.FragmentLoginBinding;
import com.qgj.shoping.network.model.User;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;


public class LoginFragment extends Fragment {


    FragmentLoginBinding binding;
    NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void loginData() {

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString();
                String password = binding.pasword.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "请输入用户名或者密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                //封装数据
                User user = new User(username, password);
                new Thread(() -> {
                    try {
                        DataModel dataModel = ServiceDaoImpl.login(user);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(() -> {
                                if (dataModel.getCode() == 200) {
                                    Toast.makeText(getContext(), dataModel.getMsg(), Toast.LENGTH_LONG).show();

                                    SizeUtils.UID = dataModel.getData().getId();
                                    navController.navigate(R.id.userFragment);
                                } else if (dataModel.getCode() == 400) {
                                    Toast.makeText(getContext(), dataModel.getMsg(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putBoolean("login", true);
                navController.navigate(R.id.registerFragment, bundle);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginData();
    }
}
