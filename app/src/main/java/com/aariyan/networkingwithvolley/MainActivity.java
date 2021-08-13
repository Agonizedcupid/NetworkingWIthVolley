package com.aariyan.networkingwithvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.aariyan.networkingwithvolley.Fragment.FirstFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //set the fragment on container:
        setFragmentOnContainer(new FirstFragment());
    }

    //setting the fragment on container (FirstFragment())
    private void setFragmentOnContainer (Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }
}