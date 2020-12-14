package com.example.day04.model.tongpao;

import com.example.day04.app.Constants;
import com.example.day04.base.BaseModel;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.tongpao.IBigImage;
import com.example.day04.interfaces.tongpao.IDown;
import com.example.day04.net.CommonSubscriber;
import com.example.day04.net.HttpManager;
import com.example.day04.utils.ImageLoader;
import com.example.day04.utils.RxUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class BigImageModel extends BaseModel implements IBigImage.Model, IDown.DownModel {
    /**
     * 下载图片
     * @param url
     * @param callback
     */
    @Override
    public void downImage(String url, ICallBack callback) {
        String[] arr = ImageLoader.splitUrl(url);
        String baseUrl = arr[0];
        String imgName = arr[1];
        String path = arr[2];
        Disposable disposable = HttpManager.getInstance().getImageApi(baseUrl)
                .downImage(imgName)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ResponseBody>(callback) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        FileOutputStream fileOutputStream = null;
                        //拿到流写入本地
                        try {
                            //判断当前的流是否有数据
                            if(inputStream.available() > 0){
                                //判断当前本地的路径是否存在
                                File file = new File(Constants.PATH_IMGS);
                                if(file.isDirectory() && !file.exists()){
                                    boolean bool = file.createNewFile();
                                    if(bool){
                                        fileOutputStream = new FileOutputStream(path);
                                        int n = 0;
                                        byte[] bytes = new byte[4096];
                                        while((n=inputStream.read(bytes)) != -1){
                                            fileOutputStream.write(bytes);
                                        }
                                        fileOutputStream.flush(); //刷新到sd卡
                                    }else{
                                        callback.fail("创建本地目录失败");
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                inputStream.close();
                                fileOutputStream.close();
                                callback.success(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void getBigImage() {

    }
}
