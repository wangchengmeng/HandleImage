package com.meng.interest.bitmaphandledemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * @author wangchengmeng
 */

public class ImageUtils {
    public static Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float scale) {
        //bitmap是不可以在原图上进行操作的 所以先copy一张
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建画布
        Canvas canvas = new Canvas(bmp);
        //创建画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//消除锯齿
        //调整颜色 色相 矩阵
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);//0 1 2 对应RGB
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);
        //调整 饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        //调整亮度
        ColorMatrix scaleMatrix = new ColorMatrix();
        scaleMatrix.setScale(scale, scale, scale, 1);
        //将三个矩阵融合
        ColorMatrix setMatrix = new ColorMatrix();
        setMatrix.postConcat(hueMatrix);
        setMatrix.postConcat(saturationMatrix);
        setMatrix.postConcat(scaleMatrix);
        //给画笔添加颜色过滤 矩阵
        paint.setColorFilter(new ColorMatrixColorFilter(setMatrix));
        //画图
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return bmp;
    }

    public static Bitmap handleImageCameo(Bitmap bitmap) {
        //通过改变像素的 argb 改变图片
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //将bitmap的像素点获取出来保存到一个数组中
        int[] oldPix = new int[width * height];
        int[] newPix = new int[width * height];
        bmp.getPixels(oldPix, 0, width, 0, 0, width, height);
        //分解出像素的 argb
        int color;
        int a, r, g, b;
        for (int i = 0; i < oldPix.length; i++) {
            color = oldPix[i];
            r = Color.red(color);//通过该方法获取到像素点中r的分量
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //修改 rgb的值  不同算法修改  argb可以实现不同的效果
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }
            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }
            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            //将改变后的argb 保存到一个像素点中 并存入数组
            newPix[i] = Color.argb(a, r, g, b);
        }
        //将改变后的像素点 设置给bitmap此刻 图片已经发生改变
        bmp.setPixels(newPix, 0, width, 0, 0, width, height);
        return bmp;
    }
}
