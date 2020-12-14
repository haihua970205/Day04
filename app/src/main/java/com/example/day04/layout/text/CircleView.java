package com.example.day04.layout.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.day04.R;
import com.example.day04.utils.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;

public class CircleView extends View {

    private Paint paint; //画笔
    private int x, y;
    private int r = 400;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    //Canvas 画纸
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = r;
        y = r;
//        canvas = new Canvas();
        canvas.drawCircle(x * 2, y * 2, r, paint);

        //水印展示
        paint.setColor(Color.GRAY);
        paint.setTextSize(100);
        paint.setStrokeWidth(2);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextScaleX(1.2f);
        paint.setUnderlineText(true);
        paint.setTextSkewX(-0.45f);
        paint.setShadowLayer(2, 10, 10, Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);  //FILL 内容填充   FILL_AND_STROKE边框和内容都填充   STROKE边框填充
        canvas.drawText("Hello World", 20, y-10, paint);//字体坐标 x,y


        //图像展示
        final Bitmap bmm = ImageLoader.getIconBitmap(getContext(), R.mipmap.ic_launcher);
//        Bitmap drawable = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bmm, x, y, paint);//图像坐标 x,y

        // 保存绘图为本地图片
        canvas.save();
        canvas.restore();

        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + "/share_pic.png");// 保存到sdcard根目录下，文件名为share_pic.png
        Log.e("111", Environment.getExternalStorageDirectory().getPath());
        FileOutputStream fos = null;
        Log.e("111","保存成功");
        try {
            fos = new FileOutputStream(file);
            bmm.compress(Bitmap.CompressFormat.PNG, 50, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
        Paint paint = new Paint();
        paint.setARGB(a,r,g,b);//设置画笔的颜色。四个参数分别代表透明度和颜色的RGB值，取值范围为0-255
        paint.setAlpha(a);//设置画笔的Alpha值。范围为0-255。0代表完全透明，255代表完全不透明
        paint.setAntiAlias(true);//设置画笔的锯齿效果。true代表抗锯齿，false代表不抗锯齿
        paint.setColor(R.color.red);//设置画笔的颜色。参数为int类型。
        paint.setColorFilter(ColorFilter colorfilter);//设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果
        paint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰.
        paint.setFakeBoldText(true);//模拟实现粗体文字，设置在小字体上效果会非常差
        paint.setFilterBitmap(true);//如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，加快显示速度，本设置项依赖于dither和xfermode的设置
        paint.setFlags(Paint.ANTI_ALIAS_FLAG)//根据flag值来对画笔进行设置。例如这里设置的是抗锯齿
        paint.setMaskFilter(MaskFilter maskfilter);//设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等
        paint.setPathEffect(PathEffect effect);//设置绘制路径的效果，如点画线等
        paint.setShader(Shader shader);//设置图像效果，使用Shader可以绘制出各种渐变效果
        paint.setShadowLayer(float radius ,float dx,float dy,int color);//在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
        paint.setStrikeThruText(boolean strikeThruText);//设置带有删除线的效果
        paint.setStrokeCap(Paint.Cap.ROUND);//当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆角形样式Cap.ROUND,或方形样式Cap.SQUARE。这个会影响画笔的始末端
        paint.setSrokeJoin(Paint.Join join);//设置绘制时各图形的结合方式，如平滑效果等
        paint.setStrokeWidth (float width);//当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度即宽度。
        paint.setStyle(Paint.Style style);//设置画笔的样式，为FILL(实心的)，FILL_OR_STROKE，或STROKE（空心的）
        paint.setSubpixelText(boolean subpixelText);//设置该项为true，将有助于文本在LCD屏幕上的显示效果
        paint.setTextAlign(Paint.Align align);//设置绘制文字的对齐方向
        paint.setTextScaleX(float scaleX);//设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果
        paint.setTextSize(float textSize);//设置绘制文字的字号大小
        paint.setTextSkewX(float skewX);//设置斜体文字，skewX为倾斜弧度
        paint.setTypeface(Typeface typeface);//设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等
        paint.setUnderlineText(boolean underlineText);//设置带有下划线的文字效果 */
