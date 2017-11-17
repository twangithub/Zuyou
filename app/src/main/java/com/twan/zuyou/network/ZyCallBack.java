package com.twan.zuyou.network;

import com.twan.zuyou.api.Result;import com.twan.zuyou.entity.Room;import com.twan.zuyou.util.LogUtil;import com.twan.zuyou.util.ToastUtil;import java.util.List;import retrofit2.Call;import retrofit2.Callback;import retrofit2.Response;

/**
 * Created by twan on 2017/11/10.
 */

public abstract class ZyCallBack<T> implements Callback{
    protected Result<T> mResult;
    protected T mRealData;

    @Override
    public void onResponse(Call call, Response response) {
        mResult =(Result<T>) response.body();
        mRealData = mResult.data;
        LogUtil.d(response.raw().request().url() +" 请求成功 , realData = "+ mRealData);
        onResponse();
    }

    public abstract void onResponse();

    @Override
    public void onFailure(Call call, Throwable t) {
        ToastUtil.show("请求失败,请重试!");
        LogUtil.wtf(t.toString());
    }
}
