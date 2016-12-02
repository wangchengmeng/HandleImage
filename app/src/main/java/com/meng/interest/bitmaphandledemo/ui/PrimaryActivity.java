package com.meng.interest.bitmaphandledemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.meng.interest.bitmaphandledemo.R;
import com.meng.interest.bitmaphandledemo.utils.ImageUtils;

/**
 * @author wangchengmeng
 * @desc
 * @更新时间
 */

public class PrimaryActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static final int MAX_VALUE = 255;
    private static final int MID_VALUE = 127;

    private Bitmap    mBitmap;
    private ImageView mIvBitmap;
    private SeekBar   mSbHue;
    private SeekBar   mSbSaturation;
    private SeekBar   mSbScale;
    private float     mHueNum;
    private float     mSaturationNum;
    private float     mScaleNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        initViews();
    }

    private void initViews() {
        mIvBitmap = (ImageView) findViewById(R.id.image_bitmap);
        mSbHue = (SeekBar) findViewById(R.id.sb_hue);
        mSbSaturation = (SeekBar) findViewById(R.id.sb_saturation);
        mSbScale = (SeekBar) findViewById(R.id.sb_scale);
        mSbHue.setOnSeekBarChangeListener(this);
        mSbSaturation.setOnSeekBarChangeListener(this);
        mSbScale.setOnSeekBarChangeListener(this);

        mSbHue.setMax(MAX_VALUE);
        mSbHue.setProgress(MID_VALUE);
        mSbSaturation.setMax(MAX_VALUE);
        mSbSaturation.setProgress(MID_VALUE);
        mSbScale.setMax(MAX_VALUE);
        mSbScale.setProgress(MID_VALUE);


        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_1);
        mIvBitmap.setImageBitmap(mBitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_hue:
                mHueNum = (progress * 1.0f - MID_VALUE) / MID_VALUE * 180;
                break;
            case R.id.sb_saturation:
                mSaturationNum = progress * 1.0f / MID_VALUE;
                break;
            case R.id.sb_scale:
                mScaleNum = progress * 1.0f / MID_VALUE;
                break;
        }
        if (null != mBitmap) {
            Log.d("aaa", "progress:" + progress);
            Bitmap bitmap = ImageUtils.handleImageEffect(mBitmap, mHueNum, mSaturationNum, mScaleNum);

            mIvBitmap.setImageBitmap(bitmap);
        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
