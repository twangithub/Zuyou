package com.twan.zuyou.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {
    public static final String FR1_COLOR="#7B1FA2";
    public static final String FR2_COLOR="#5D4037";
    public static final String FR3_COLOR="#FF9800";

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "twan" + File.separator + "zuyou";

}
