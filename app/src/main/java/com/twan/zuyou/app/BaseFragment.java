package com.twan.zuyou.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;import com.twan.zuyou.api.Result;import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected Context mContext;
    protected View mRootView;
    protected Result mData;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       mRootView = LayoutInflater.from(mActivity).inflate(getLayoutId(), container, false);
       ButterKnife.bind(this,mRootView);
       initData(getArguments());
       initView(mRootView, savedInstanceState);
       mIsPrepare = true;
       onLazyLoad();
       setListener();
       return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 执行数据的加载
     */
    protected abstract void initData(Bundle arguments);

    protected void setListener(){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser){
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser(){
        if (mIsPrepare && mIsVisible){
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad(){

    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id){
        if (mRootView == null){
            return null;
        }
        return (T) mRootView.findViewById(id);
    }

}
