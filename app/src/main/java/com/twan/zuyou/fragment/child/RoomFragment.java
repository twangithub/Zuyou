package com.twan.zuyou.fragment.child;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
    private List<String> mData;
    private ItemDragAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_clock_1;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity,2));
        mData = generateData(50);
        OnItemDragListener listener = new OnItemDragListener() {
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
        };
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                LogUtil.d("view swiped start: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                LogUtil.d("View reset: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                LogUtil.d("View Swiped: " + pos);
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(mActivity, R.color.color_light_blue));
//                canvas.drawText("Just some text", 0, 40, paint);
            }
        };

        mAdapter = new ItemDragAdapter(mData);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);
        mAdapter.enableDragItem(mItemTouchHelper);
        mAdapter.setOnItemDragListener(listener);
//        mRecyclerView.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));

        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                ToastUtils.showShortToast("点击了" + position);
//            }
//        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //ToastUtil.show("点击了" + position);

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

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add("item " + i);
        }
        return data;
    }

    @Override
    protected void initData(Bundle arguments) {
//        Api.getApiService().getRooms().enqueue(new ZyCallBack<Result<List<Room>>>() {
//            @Override
//            public void onResponse() {
//                ToastUtil.show("数据加载成功");
//            }
//        });

    }


}
