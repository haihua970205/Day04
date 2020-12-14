package com.example.day04.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


import com.example.day04.app.Constants;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.net.HttpManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 下载服务
 */
public class DownService extends Service{

    public static String TAG = DownService.class.getCanonicalName();
    private MyBinder myBinder;

    public DownService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return new MyBinder();//MyBind()继承Binder  Binder实现了Ibinder
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
    }

    //下载图片
    public class MyBinder extends Binder {
        public void downImg(String url) {
            //耗时操作的方法
            @SuppressLint("StaticFieldLeak")
            AsyncTask asyncTask = new AsyncTask<Object, Long, Void>() {
                @Override
                protected Void doInBackground(Object... voids) {//不定参数（不固定） 可以写一个写两个
                    //第一步基础地址
                    if (voids.length > 0) {
                        //https://tpcdn.whfpsoft.com:443/File/headPhoto/20200404/fa5134d048f08eff6f3617dc35d3a836.jpg
                        String imgUrl = (String) voids[0];

                        //end保存这个路径的 https://tpcdn.whfpsoft.com:443/
                        int end = imgUrl.lastIndexOf("/") + 1;
                        //这个截取的 File/headPhoto/20200404/ 基础地址
                        String baseUrl = imgUrl.substring(0, end);
                        //fa5134d048f08eff6f3617dc35d3a836.jpg  图片文件的名字
                        String imgName = imgUrl.substring(end, imgUrl.length());

                        Call<ResponseBody> call = (Call<ResponseBody>) HttpManager.getInstance().getImageApi(baseUrl).downImage(imgName);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    //读取文件流
                                    InputStream inputStream = response.body().byteStream();
                                    //判断当前的流是否有数据
                                    if (inputStream.available() > 0) {
                                        File file = new File(Constants.PATH_IMGS);
                                        if (file.isDirectory() && !file.exists()) {
                                            boolean bool = file.createNewFile();//判断文件是否下载
                                            if (bool) {
                                                String imgPath = Constants.PATH_IMGS + "/" + imgName;//本地保存图片的地址
                                                FileOutputStream fileOutputStream = new FileOutputStream(imgPath);
                                                int len = 0;
                                                byte[] by = new byte[4096];
                                                while ((len = inputStream.read(by)) != -1) {
                                                    fileOutputStream.write(by);
                                                }
                                                fileOutputStream.flush();//刷新到sd卡
                                                fileOutputStream.close();
                                                inputStream.close();
                                            }
                                        }
                                    } else {
                                        Log.i(TAG, "onResponse: " + "创建失败");
                                    }
                                } catch (Exception e) {
                                    Log.i(TAG, e.getMessage() + "保存失败");
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.i(TAG, t.getMessage() + "保存失败");
                            }
                        });
                    } else {
                        Log.i(TAG, "下载失败");
                    }
                    return null;
                }
            };
            asyncTask.execute(url);
        }
    }
}
