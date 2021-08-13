package com.aariyan.networkingwithvolley.Interface;

import com.aariyan.networkingwithvolley.Model.DataModel;

import java.util.List;

public interface GenericCallback {

    void onSuccess (List<DataModel> list);
    void onError(String errorMessage);
}
