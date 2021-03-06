package com.twan.zuyou.fragment.child;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.twan.mylibary.recyclerViewHelper.BaseQuickAdapter;
import com.twan.mylibary.recyclerViewHelper.BaseViewHolder;
import com.twan.mylibary.recyclerViewHelper.callback.ItemDragAndSwipeCallback;
import com.twan.mylibary.recyclerViewHelper.listener.OnItemClickListener;
import com.twan.mylibary.recyclerViewHelper.listener.OnItemDragListener;
import com.twan.mylibary.recyclerViewHelper.listener.OnItemSwipeListener;
import com.twan.zuyou.R;
import com.twan.zuyou.adapter.ItemDragAdapter;
import com.twan.zuyou.api.Api;
import com.twan.zuyou.api.Result;
import com.twan.zuyou.app.BaseFragment;
import com.twan.zuyou.entity.Room;
import com.twan.zuyou.network.ZyCallBack;
import com.twan.zuyou.ui.MainActivity;
import com.twan.zuyou.util.LogUtil;
import com.twan.zuyou.util.ToastUtil;
import com.twan.zuyou.widget.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by twan on 2017/10/31.
 */
public class RoomFragment extends BaseFragment {

    @BindView(R.id.rv_list) RecyclerView mRecyclerView;
    private ItemDragAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_clock_1;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mBaseRecyclerView=mRecyclerView;
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity,2));
        mAdapter = new ItemDragAdapter((List<Room>)mData);
        initRecycleView(mAdapter,new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                LogUtil.d("drag start");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                LogUtil.d("move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                LogUtil.d( "drag end");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }
        },new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                View contentView = LayoutInflater.from(mActivity).inflate(R.layout.pop_item_click,null);
                //处理popWindow 显示内容
                //handleLogic(contentView);
                //创建并显示popWindow
                new CustomPopWindow.PopupWindowBuilder(mActivity)
                        .setView(contentView)
                        .enableBackgroundDark(true) //0，背景是否变暗
                        .setBgDarkAlpha(0.8f) // 控制亮度
                        .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                Log.e("TAG","onDismiss");
                            }
                        })
                        .create()
                        .showAtLocation(MainActivity.instance.getMainBoard(), Gravity.CENTER,0,0);
            }
        });

    }

    @Override
    protected void onLazyLoad() {
        mAdapter.getData().clear();
        Api.getApiService().getRooms().enqueue(new ZyCallBack<Result<List<Room>>>() {
            @Override
            public void onResponse() {
                mData = mRealData;
                mAdapter.addData((List<Room>)mData);
            }
        });
    }
}
