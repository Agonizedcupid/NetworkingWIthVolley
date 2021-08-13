package com.aariyan.networkingwithvolley.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constant {
    // Baser URL for parsing the JSON:
    public static final String URL = "https://pixabay.com/api/?key=22748254-b3b4a45c093cf8e27842d3fc6&q=yellow+flowers&image_type=photo&pretty=true/";


    //checking whether the mobile is connected with network or not:
    public static boolean isInternetConnected (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if (info != null) {
                for (int i=0; i<info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
