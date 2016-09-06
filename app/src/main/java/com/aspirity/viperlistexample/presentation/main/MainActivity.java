package com.aspirity.viperlistexample.presentation.main;

import android.content.Intent;
import android.os.Bundle;

import com.aspirity.viperlistexample.R;
import com.aspirity.viperlistexample.presentation.BaseActivity;
import com.aspirity.viperlistexample.presentation.Layout;
import com.aspirity.viperlistexample.presentation.details.DetailsActivity;

@Layout(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainRouter {

    public static final String IMAGE_URL = "IMAGE_URL";
    public static final String IMAGE_TITLE = "IMAGE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            displayFragment(ListFragment.newInstance(), R.id.frame_container, false);
        }
    }

    public void updateToolbar(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showFullSizeImage(String url, String title) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(IMAGE_URL, url);
        intent.putExtra(IMAGE_TITLE, title);
        startActivity(intent);
    }
}