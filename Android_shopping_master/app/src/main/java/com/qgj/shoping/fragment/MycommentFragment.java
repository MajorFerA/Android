package com.qgj.shoping.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.qgj.shoping.R;
import com.qgj.shoping.databinding.FragmentMycommentBinding;
import com.qgj.shoping.network.model.BuyModel;
import com.qgj.shoping.network.model.ShoppingModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;

import java.io.IOException;


public class MycommentFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {


    FragmentMycommentBinding binding;
    NavController navController;
    private int score = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMycommentBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //封装数量 用户 id 和 gid
                BuyModel buyModel = new BuyModel();
                buyModel.setId(getArguments().getInt("id"));
                buyModel.setPrice(getArguments().getDouble("price"));
                buyModel.setNum(getArguments().getInt("num"));
                buyModel.setTotal(getArguments().getDouble("total"));
                buyModel.setScore(score);
                buyModel.setEvaluation(binding.etComment.getText().toString().trim());
                new Thread(() -> {
                    try {
                        ShoppingModel shopping = ServiceDaoImpl.orderEvaluate(buyModel);
                        if (shopping.getCode() != 400) {
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(() -> {
                                    hintKeyBoards(binding.etComment);
                                    Toast.makeText(getContext(), "评价成功", Toast.LENGTH_SHORT).show();
                                    navController.popBackStack();
                                });
                            }
                            return;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        });
        binding.rgScore.setOnCheckedChangeListener(this);

        if (getArguments() != null) {
            Glide.with(this).load(ServiceDaoImpl.URL + "/" + getArguments().getString("img")).into(binding.img);
            binding.info.setText("\u3000\u3000" + getArguments().getString("info"));
            if (getArguments().getInt("score") == 1) {
                binding.rgScore.check(R.id.mc_rb1);
            } else if (getArguments().getInt("score") == 2) {
                binding.rgScore.check(R.id.mc_rb2);
            } else if (getArguments().getInt("score") == 3) {
                binding.rgScore.check(R.id.mc_rb3);
            } else if (getArguments().getInt("score") == 4) {
                binding.rgScore.check(R.id.mc_rb4);
            } else if (getArguments().getInt("score") == 5) {
                binding.rgScore.check(R.id.mc_rb5);
            } else {
                binding.rgScore.check(R.id.mc_rb5);
            }
            binding.etComment.setText(getArguments().getString("evaluation"));

            if (getArguments().getInt("ostate") == 1) {
                binding.evaluatetime.setText("评价时间:"+getArguments().getString("evaluatetime"));
                binding.evaluatetime.setVisibility(View.VISIBLE);
                binding.submit.setVisibility(View.GONE);
            }else {
                binding.evaluatetime.setVisibility(View.GONE);
                binding.submit.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.mc_rb1:
                score = 1;
                break;
            case R.id.mc_rb2:
                score = 2;
                break;
            case R.id.mc_rb3:
                score = 3;
                break;
            case R.id.mc_rb4:
                score = 4;
                break;
            case R.id.mc_rb5:
                score = 5;
                break;
        }
    }

    public static void hintKeyBoards(View view) {
        InputMethodManager manager = ((InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}