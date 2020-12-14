package com.example.day04.app;

import java.io.File;

public class Constants {

    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_IMGS = PATH_DATA + "/tp/imgs";
}
