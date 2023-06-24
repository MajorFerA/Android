package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.qgj.shoping.R;
import com.qgj.shoping.adapter.GoodAdapter;
import com.qgj.shoping.databinding.FragmentHomeBinding;
import com.qgj.shoping.network.model.GoodModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;

import java.io.IOException;


public class homeFragment extends Fragment {

    FragmentHomeBinding binding;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData(null);
        initBth();
    }

    private void initData(String info) {
        new Thread(() -> {
            try {
                GoodModel goodAll = ServiceDaoImpl.getGoodAll(info);
                //设置数据
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        GoodAdapter adapter = new GoodAdapter(goodAll.getData(), getContext(), navController);
                        binding.shopping.setAdapter(adapter);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void initView() {
        binding.shopping.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void initBth() {
        //去地图页面
        binding.mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("mBtn1", true);
                navController.navigate(R.id.MapFragment, bundle);
            }
        });
        //去扫一扫页面
        binding.mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("mBtn2", true);
                navController.navigate(R.id.ScanFragment, bundle);
            }
        });

        //去扫一扫页面
        binding.sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyStr = binding.search.getText().toString();
                initData(keyStr);
            }
        });
    }
}