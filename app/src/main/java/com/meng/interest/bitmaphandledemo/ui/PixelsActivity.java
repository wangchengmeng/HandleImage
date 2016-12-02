package com.meng.interest.bitmaphandledemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.meng.interest.bitmaphandledemo.R;
import com.meng.interest.bitmaphandledemo.utils.ImageUtils;

public class PixelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels);
        initViews();
    }

    private void initViews() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_1);

        ImageView imageView_1 = (ImageView) findViewById(R.id.image_1);
        ImageView imageView_2 = (ImageView) findViewById(R.id.image_2);
        ImageView imageView_3 = (ImageView) findViewById(R.id.image_3);
        ImageView imageView_4 = (ImageView) findViewById(R.id.image_4);

        Bitmap bitmap1 = ImageUtils.handleImageCameo(bitmap);
        imageView_1.setImageBitmap(bitmap1);
        imageView_2.setImageBitmap(bitmap);

    }

}
