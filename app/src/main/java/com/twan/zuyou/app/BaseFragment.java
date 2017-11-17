package com.twan.zuyou.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twan.mylibary.recyclerViewHelper.BaseItemDraggableAdapter;
import com.twan.mylibary.recyclerViewHelper.callback.ItemDragAndSwipeCallback;
import com.twan.mylibary.recyclerViewHelper.listener.OnItemClickListener;
import com.twan.mylibary.recyclerViewHelper.listener.OnItemDragListener;
import com.twan.zuyou.adapter.ItemDragAdapter;
import com.twan.zuyou.api.Result;
import com.twan.zuyou.entity.Room;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseFragment<T> extends Fragment {

    protected Activity mActivity;
    protected Context mContext;
    protected View mRootView;
    protected T mData;
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
    protected <V extends View> V findViewById(int id){
        if (mRootView == null){
            return null;
        }
        return (V) mRootView.findViewById(id);
    }

    // 初始化recycleview
    protected RecyclerView mRecyclerView;

    protected void initRecycleView(BaseItemDraggableAdapter adapter, OnItemDragListener listener, OnItemClickListener listener2){
        ItemTouchHelper mItemTouchHelper;
        ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        adapter.enableDragItem(mItemTouchHelper);
        adapter.setOnItemDragListener(listener);
//        mRecyclerView.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(listener2);
    }

}
