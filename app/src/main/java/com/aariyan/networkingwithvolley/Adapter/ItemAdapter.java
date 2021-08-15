package com.aariyan.networkingwithvolley.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.networkingwithvolley.Fragment.SecondFragment;
import com.aariyan.networkingwithvolley.Interface.GenericCallback;
import com.aariyan.networkingwithvolley.Interface.onClickListener;
import com.aariyan.networkingwithvolley.Model.DataModel;
import com.aariyan.networkingwithvolley.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    //Context instance variable to take context
    private Context context;
    //list for containing all the list items
    private List<DataModel> list;
    private Activity activity;

    //public constructor for initializing the data:
    public ItemAdapter(Context context, List<DataModel> list,Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        //inflating each row
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemAdapter.ViewHolder holder, int position) {

        //mapping the data with model class:
        DataModel model = list.get(position);

        //setting the total comment:
        holder.commentCount.setText("" + model.getComments());
        //setting the total like
        holder.likeCount.setText("" + model.getLikes());
        //setting the image
        Glide.with(context).load(model.getImageUrl()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(holder.itemImage);

        //For opening new fragment for the particular items
        holder.setOnClickListener(new onClickListener() {
            @Override
            public void onClick(View view, int position) {
                //creating the object of secondFragment:
                SecondFragment fragment = new SecondFragment();
                // for passing data from one fragment to another
                Bundle bundle = new Bundle();
                bundle.putString("name",model.getUserName());
                bundle.putString("imageUrl",model.getImageUrl());
                fragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        //total row count
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImage;
        private TextView likeCount, commentCount;

        public onClickListener onClickListener;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.listImage);
            likeCount = itemView.findViewById(R.id.likeCount);
            commentCount = itemView.findViewById(R.id.commentCount);
            itemView.setOnClickListener(this);

        }

        public void setOnClickListener(onClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v, getAdapterPosition());
        }
    }
}
