package com.qgj.shoping.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qgj.shoping.databinding.CellBinding;
import com.qgj.shoping.databinding.ItemShoppingBinding;
import com.qgj.shoping.network.model.BuyModel;
import com.qgj.shoping.network.model.DataModel;
import com.qgj.shoping.network.model.OrderModel;
import com.qgj.shoping.network.model.ShoppingModel;
import com.qgj.shoping.network.service.ServiceDaoImpl;
import com.qgj.shoping.view.SizeUtils;

import java.io.IOException;
import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    List<OrderModel.DataDTO> datas;
    DataModel dataModel;
    Context mContext;
    CellBinding cellBinding;

    public ShoppingAdapter(List<OrderModel.DataDTO> datas, DataModel dataModel, Context context, CellBinding cellBinding) {
        this.datas = datas;
        this.dataModel = dataModel;
        mContext = context;
        this.cellBinding = cellBinding;
    }

    @NonNull
    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShoppingBinding binding = ItemShoppingBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new ShoppingAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //数据元
        OrderModel.DataDTO data = datas.get(position);
        Log.e("TGA", data.toString());
        //内容
        holder.mBinding.info.setText("\u3000\u3000" + data.getGoodname());
        //图片
        Glide.with(mContext).load(ServiceDaoImpl.URL + "/" + data.getImg()).into(holder.mBinding.img);
        //价格
        holder.mBinding.price.setText("单价: ¥" + data.getPrice());
        //数量
        holder.mBinding.num.setText("数量:" + data.getNum());
        holder.mBinding.total.setText("合计:" + data.getTotal());

        //删除该id
        holder.mBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("您确定删除吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread(() -> {
                                    try {
                                        ShoppingModel shoppingModel = ServiceDaoImpl.deleteShoppingById(data.getId());
                                        Log.e("DD", "" + shoppingModel);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }).start();
                                datas.remove(position);
                                ShoppingAdapter.this.notifyItemRemoved(position);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        holder.mBinding.bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cellBinding.etReceiver.setText(dataModel.getData().getUsername());
                cellBinding.etPhone.setText(dataModel.getData().getPhone());
                cellBinding.etShippingaddr.setText(dataModel.getData().getShippingaddr());

                //fix bug: The specified child already has a parent. You must call removeView() on the child's parent first
                if(cellBinding.dialog.getParent()!=null){
                    ((ViewGroup) cellBinding.dialog.getParent()).removeView(cellBinding.dialog);
                }

                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("\n您确定要支付吗?")
                        .setView(cellBinding.dialog)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cellBinding.etReceiver.getText().toString().trim().length() == 0) {
                                    new AlertDialog.Builder(mContext)
                                            .setTitle("提示")
                                            .setMessage("收货人不能为空！")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .show();
                                } else if (cellBinding.etPhone.getText().toString().trim().length() != 11) {
                                    new AlertDialog.Builder(mContext)
                                            .setTitle("提示")
                                            .setMessage("请填写正确的11位手机号码！")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .show();
                                } else if (cellBinding.etShippingaddr.getText().toString().trim().length() == 0) {
                                    new AlertDialog.Builder(mContext)
                                            .setTitle("提示")
                                            .setMessage("收货地址不能为空！")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .show();
                                } else {
                                    //封装数量 用户 id 和 gid
                                    BuyModel buyModel = new BuyModel();
                                    buyModel.setId(data.getId());
                                    buyModel.setGid(data.getGid());
                                    buyModel.setPrice(data.getPrice());
                                    buyModel.setTotal(data.getTotal());
                                    buyModel.setReceiver(cellBinding.etReceiver.getText().toString().trim());
                                    buyModel.setPhone(cellBinding.etPhone.getText().toString().trim());
                                    buyModel.setShippingaddr(cellBinding.etShippingaddr.getText().toString().trim());
                                    buyModel.setStatus("交易成功");
                                    buyModel.setNum(data.getNum());
                                    buyModel.setUid(SizeUtils.UID);
                                    new Thread(() -> {
                                        try {
                                            ServiceDaoImpl.addOrder(buyModel);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }).start();
                                    //把 商品id 传过去
                                    datas.remove(position);
                                    ShoppingAdapter.this.notifyItemRemoved(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemShoppingBinding mBinding;

        public ViewHolder(ItemShoppingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
