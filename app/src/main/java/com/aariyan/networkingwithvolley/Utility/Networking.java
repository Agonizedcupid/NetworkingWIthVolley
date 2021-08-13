package com.aariyan.networkingwithvolley.Utility;

import android.content.Context;

import com.aariyan.networkingwithvolley.Interface.GenericCallback;
import com.aariyan.networkingwithvolley.Model.DataModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Networking {

    //Instance variable for context:
    private Context context;

    //volley member variable:
    private RequestQueue requestQueue;

    //public constructor for initiating the value:
    public Networking(Context context) {
        //instantiate the constructor:
        this.context = context;

        //instantiate the volley:
        requestQueue = Volley.newRequestQueue(context);
    }

    /*
    getting the JSON data from the network through the API:
     */
    public void gettingJSONDataFromURL (String url, GenericCallback callback) {

        //if, internet in not connected then show a message to the user:
        if (!Constant.isInternetConnected(context)) {
            callback.onError("Please check your internet connection!");
        } else { //if, mobile is connected with the internet:
            JsonObjectRequest rootRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //checking if response is not null:
                    if (response != null) {
                        try {
                            //creating the list to add data:
                            List<DataModel> listItems = new ArrayList<>();

                            //taking array items
                            JSONArray hitsArray = response.getJSONArray("hits");
                            //taking all the items from array by a loop:
                            for (int i=0; i<hitsArray.length(); i++) {
                                JSONObject singleItem = hitsArray.getJSONObject(i);
                                //populating data with model class:
                                DataModel model = new DataModel(
                                        singleItem.getString("user"),
                                        singleItem.getInt("likes"),
                                        singleItem.getInt("comments"),
                                        singleItem.getString("webformatURL")
                                );
                                //adding data to the list:
                                listItems.add(model);
                            }

                            callback.onSuccess(listItems);

                        }catch (Exception e) {
                            callback.onError(e.getMessage().toString());
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    callback.onError(e.getMessage().toString());
                }
            });

            requestQueue.add(rootRequest);
        }
    }

}
