package com.aariyan.networkingwithvolley.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aariyan.networkingwithvolley.Adapter.ItemAdapter;
import com.aariyan.networkingwithvolley.Interface.GenericCallback;
import com.aariyan.networkingwithvolley.MainActivity;
import com.aariyan.networkingwithvolley.Model.DataModel;
import com.aariyan.networkingwithvolley.R;
import com.aariyan.networkingwithvolley.Utility.Constant;
import com.aariyan.networkingwithvolley.Utility.Networking;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FirstFragment extends Fragment {

    //For inflating fragment layout:
    private View root;
    //RecyclerView Instance variable:
    private RecyclerView itemRecyclerView;

    //instance variable for adapter:
    private ItemAdapter adapter;

    //shimmer layout instance variable:
    private ShimmerFrameLayout shimmerLayout;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void getDataFromNetwork(Context context) {
        //creating the object of networking class:
        Networking networking = new Networking(context);

        //passing url and implementing the interface for getting the value from network through volley:
        networking.gettingJSONDataFromURL(Constant.URL, new GenericCallback() {
            @Override
            public void onSuccess(List<DataModel> list) {

                //if data get successfully
                adapter = new ItemAdapter(requireContext(), list, getActivity());
                //setting the adapter to recyclerview:
                itemRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                //setting the recycler view visible
                itemRecyclerView.setVisibility(View.VISIBLE);

                shimmerLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);
            }

            //if find any error:
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(context, "" + errorMessage, Toast.LENGTH_SHORT).show();
//                shimmerLayout.stopShimmer();
//                shimmerLayout.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_first, container, false);

        //instantiate UI variable:
        initUI();

        //getting data through interface from the network:
        getDataFromNetwork(getContext());

        return root;
    }

    private void initUI() {
        //instantiate the recyclerview:
        itemRecyclerView = root.findViewById(R.id.itemRecyclerView);
        //setting the layout manager:
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //instantiate the shimmer Layout:
        shimmerLayout = root.findViewById(R.id.shimmerLayoutContainer);
        shimmerLayout.startShimmer();
    }
}