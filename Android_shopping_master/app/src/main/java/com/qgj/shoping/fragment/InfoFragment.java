package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.qgj.shoping.R;
import com.qgj.shoping.databinding.FragmentInfoBinding;
import com.qgj.shoping.network.model.AdminBuyModel;
import com.qgj.shoping.network.model.GoodInfoModel;
import com.qgj.shoping.network.model.ShoppingModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;

public class InfoFragment extends Fragment {

    FragmentInfoBinding binding;

    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    private  int id; //商品的id
    private void initView() {
        if (getArguments()!=null){
             id = getArguments().getInt("id");
                 new Thread(()->{
                             try {
                                 GoodInfoModel goodById = ServiceDaoImpl.getGoodById(id);
                                   if (getActivity()!=null) {
                                      getActivity().runOnUiThread(()->{
                                            initDate(goodById);
                                      });
                                   }
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                         }).start();
        }
    }
    int num =1;

    private void initDate(GoodInfoModel goodById) {
        //设置数据
        GoodInfoModel.DataDTO data = goodById.getData();
        Glide.with(getContext()).load(ServiceDaoImpl.URL+"/"+data.getImg()).into(binding.img);
        binding.info.setText(data.getInfo());
        binding.price.setText("¥"+data.getPrice());
        initBth();
        //添加和消除的按钮
        initadd();
    }
    private void initadd() {
        binding.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mum.setText(String.valueOf(++num));

            }
        });
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num ==1){
                    Toast.makeText(getContext(), "最少购买一个哦~~", Toast.LENGTH_SHORT).show();
                    return;
                }

                binding.mum.setText(String.valueOf(--num));
            }
        });
    }

    private void initBth() {
        binding.shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //封装数据
                AdminBuyModel adminBuyModel = new AdminBuyModel();
                adminBuyModel.setGid(id);
                if (SizeUtils.UID==0){ //没有登录 了
                    Toast.makeText(getContext(), "请先登录~~", Toast.LENGTH_SHORT).show();
                    return;
                }
                adminBuyModel.setUid(SizeUtils.UID);
                adminBuyModel.setNum(Integer.valueOf(binding.mum.getText().toString()));
                //发送请求添加
                addShopping(adminBuyModel);
            }
        });
    }

    private void addShopping(AdminBuyModel adminBuyModel) {
         new Thread(()->{
                     try {
                         ShoppingModel shoppingModel = ServiceDaoImpl.addShopping(adminBuyModel);
                          if (getActivity()!=null) {
                             getActivity().runOnUiThread(()->{
                                 if (shoppingModel.isData()){
                                     Toast.makeText(getContext(), "添加到购物车成功", Toast.LENGTH_SHORT).show();
                                     navController.navigate(R.id.homeFragment);
                                     return;
                                 }
                                 Toast.makeText(getContext(), "添加失败", Toast.LENGTH_SHORT).show();

                             });
                          }
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }).start();
    }
}