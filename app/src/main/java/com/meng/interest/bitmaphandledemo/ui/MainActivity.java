package com.meng.interest.bitmaphandledemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.meng.interest.bitmaphandledemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_primary:
                startActivity(new Intent(this, PrimaryActivity.class));
                break;
            case R.id.btn_color_matrix:
                startActivity(new Intent(this, ColorMatrixActivity.class));
                break;
            case R.id.btn_pixels:
                startActivity(new Intent(this, PixelsActivity.class));
                break;
        }
    }
}
