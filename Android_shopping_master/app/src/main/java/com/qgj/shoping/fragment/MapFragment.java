package com.qgj.shoping.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.baidu.mapapi.map.TextureMapView;
import com.qgj.shoping.databinding.FragmentMapBinding;

public class MapFragment extends Fragment{
    FragmentMapBinding binding;
    NavController navController;
    private TextureMapView mMapView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMapBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
