package com.qgj.shoping.view;

import android.content.Context;

public class SizeUtils {
    public  static  int UID = 0;
    public static int dipBox(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5F);
    }
}
