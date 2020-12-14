package com.example.day04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.day04.utils.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CircleView extends View {


    private Paint paint; //画笔
    private Canvas canvas;
    private int x, y;
    private int r = 200;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
//        drawBitmap();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    //Canvas 画纸
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = r;
        y = r;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(x*2, y, r, paint);
        canvas.drawArc(x*2,y*3,x*4,y*4,x*2,y*3,false,paint);

        paint.setColor(Color.GREEN);
        paint.setTextSize(150);
        paint.setStrokeWidth(4);

        canvas.drawText("smile", x, y, paint);
        final Bitmap bmm = ImageLoader.getIconBitmap(getContext(), R.mipmap.brand);
        //Bitmap drawable = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.drawBitmap(bmm, x, y, paint);


        // 保存绘图为本地图片
        canvas.save();
        canvas.restore();

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/share_pic.png");// 保存到sdcard根目录下，文件名为share_pic.png

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bmm.compress(Bitmap.CompressFormat.PNG, 500, fos);
            fos.close();
            Log.e("111", Environment.getExternalStorageDirectory().getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawBitmap() {
        Bitmap mBitmap;
        // 画布
        Canvas mCanvas;
        // 画笔－－画图片
        Paint mPicturePaint = new Paint();
        // 画笔－－写字
        Paint mTextPaint = new Paint();
        // 画笔－－写运动数据
        Paint mDataPaint = new Paint();


        Bitmap bm_bg = BitmapFactory.decodeResource(getResources(),
                R.mipmap.brand);
        // 得到图片的宽、高
        int width_bg = bm_bg.getWidth();
        int height_bg = bm_bg.getHeight();

        // 创建一个你需要尺寸的Bitmap
        mBitmap = Bitmap.createBitmap(width_bg, height_bg, Bitmap.Config.ARGB_8888);
        // 用这个Bitmap生成一个Canvas,然后canvas就会把内容绘制到上面这个bitmap中
        mCanvas = new Canvas(mBitmap);

        // 绘制背景图片
        mCanvas.drawBitmap(bm_bg, 0.0f, 0.0f, mPicturePaint);
        // 绘制图片
        Bitmap bm_head = BitmapFactory.decodeResource(getResources(),
                R.mipmap.cup1);

        // 得到图片的宽、高
        int width_head = bm_head.getWidth();
        int height_head = bm_head.getHeight();
        // 绘制图片－－保证其在水平方向居中
        mCanvas.drawBitmap(bm_head, (width_bg - width_head) / 2, 0.0f,
                mPicturePaint);

        // 绘制文字
        mTextPaint.setColor(Color.WHITE);// 白色画笔
        mTextPaint.setTextSize(80.0f);// 设置字体大小

        // 绘制文字
        mDataPaint.setColor(Color.RED);// 红色画笔
        mDataPaint.setTextSize(120.0f);// 设置字体大小

        String distanceTextString = "运动距离：";
        String distanceDataString = String.valueOf(888);
        String distanceScalString = "米";

        float distanceTextString_width = mTextPaint.measureText(
                distanceTextString, 0, distanceTextString.length());

        float distanceDataString_width = mDataPaint.measureText(
                distanceDataString, 0, distanceDataString.length());
        float distanceScalString_width = mTextPaint.measureText(
                distanceScalString, 0, distanceScalString.length());
        float x = (width_bg - distanceTextString_width
                - distanceDataString_width - distanceScalString_width) / 2;

        mCanvas.drawText(distanceTextString, x, height_head, mTextPaint);// 绘制文字
        mCanvas.drawText(distanceDataString, x + distanceTextString_width,
                height_head, mDataPaint);// 绘制文字

        mCanvas.drawText(distanceScalString, x + distanceTextString_width
                + distanceDataString_width, height_head, mTextPaint);// 绘制文字

        // 保存绘图为本地图片
        mCanvas.save();
        mCanvas.restore();

        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + "/share_pic.png");// 保存到sdcard根目录下，文件名为share_pic.png
        Log.e("111", Environment.getExternalStorageDirectory().getPath());
        FileOutputStream fos = null;
        Log.e("111","保存成功");
        try {
            fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
