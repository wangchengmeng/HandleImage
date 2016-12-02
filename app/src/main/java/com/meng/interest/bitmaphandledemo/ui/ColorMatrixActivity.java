package com.meng.interest.bitmaphandledemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.meng.interest.bitmaphandledemo.R;

import java.util.Random;

/**
 * @author wangchengmeng
 * @desc
 * @更新时间
 */

public class ColorMatrixActivity extends AppCompatActivity {

    private float[]   mColorMatrix;
    private ImageView mIvBitmap;
    private Bitmap    mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        initVariable();
        initViews();
        initMatrix();
    }

    private void initVariable() {

    }

    private void initMatrix() {
        mColorMatrix = new float[20];
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                //RGBA的位置  初始化为1
                mColorMatrix[i] = 1;
            } else {
                mColorMatrix[i] = 0;
            }
        }
    }

    private void initViews() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_1);

        mIvBitmap = (ImageView) findViewById(R.id.image_bitmap);
        mIvBitmap.setImageBitmap(mBitmap);


        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变颜色矩阵
                Random random = new Random();
                mColorMatrix[random.nextInt(20)] = random.nextInt(10);
                changdeImage();
            }
        });
    }

    private void changdeImage() {
        //copy一个bitmap
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建画布
        Canvas canvas = new Canvas(bitmap);
        //创建画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //将一维数组转成矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        //给画笔设置颜色矩阵
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //画图
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mIvBitmap.setImageBitmap(bitmap);
    }
}
