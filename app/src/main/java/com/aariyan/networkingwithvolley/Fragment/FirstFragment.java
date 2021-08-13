package com.aariyan.networkingwithvolley.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aariyan.networkingwithvolley.Interface.GenericCallback;
import com.aariyan.networkingwithvolley.Model.DataModel;
import com.aariyan.networkingwithvolley.R;
import com.aariyan.networkingwithvolley.Utility.Constant;
import com.aariyan.networkingwithvolley.Utility.Networking;

import java.util.List;

public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting data through interface from the network:
        getDataFromNetwork(getContext());
    }

    private void getDataFromNetwork(Context context) {
        //creating the object of networking class:
        Networking networking = new Networking(context);

        //passing url and implementing the interface for getting the value from network through volley:
        networking.gettingJSONDataFromURL(Constant.URL, new GenericCallback() {
            @Override
            public void onSuccess(List<DataModel> list) {
                //if data get successfully
                Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();
            }

            //if find any error:
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(context, ""+errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}