package com.aariyan.networkingwithvolley.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.networkingwithvolley.Model.DataModel;
import com.aariyan.networkingwithvolley.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    //Context instance variable to take context
    private Context context;
    //list for containing all the list items
    private List<DataModel> list;

    //public constructor for initializing the data:
    public ItemAdapter(Context context,List<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        //inflating each row
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemAdapter.ViewHolder holder, int position) {

        //mapping the data with model class:
        DataModel model = list.get(position);

        //setting the total comment:
        holder.commentCount.setText(""+model.getComments());
        //setting the total like
        holder.likeCount.setText(""+model.getLikes());
        //setting the image
        Glide.with(context).load(model.getImageUrl()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        //total row count
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView likeCount,commentCount;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.listImage);
            likeCount = itemView.findViewById(R.id.likeCount);
            commentCount = itemView.findViewById(R.id.commentCount);

        }
    }
}
