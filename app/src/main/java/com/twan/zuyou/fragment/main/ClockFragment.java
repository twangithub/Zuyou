package com.twan.zuyou.fragment.main;

import android.os.Bundle;
import android.util.Log;import android.view.Gravity;import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;import android.widget.FrameLayout;
import android.widget.LinearLayout;import android.widget.PopupWindow;

import com.twan.zuyou.R;
import com.twan.zuyou.app.BaseFragment;
import com.twan.zuyou.fragment.child.AddcFragment;import com.twan.zuyou.fragment.child.AppoFragment;import com.twan.zuyou.fragment.child.GoodFragment;import com.twan.zuyou.fragment.child.HistFragment;import com.twan.zuyou.fragment.child.ProjFragment;import com.twan.zuyou.fragment.child.RoomFragment;
import com.twan.zuyou.fragment.child.TechFragment;import com.twan.zuyou.ui.MainActivity;import com.twan.zuyou.util.FragmentUtils;import com.twan.zuyou.widget.CustomPopWindow;

import java.util.ArrayList;import java.util.List;import butterknife.BindView;

/**
 * Created by twan on 2017/10/26.
 */
public class ClockFragment extends BaseFragment {
    @BindView(R.id.hRoll) LinearLayout hRoll;
    @BindView(R.id.frame) FrameLayout frame;
    List<View> views = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_clock;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        View cusviewRoom = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewRoom.setTag(1);
        View cusviewTech = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewTech.setTag(2);
        View cusviewProj = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewProj.setTag(3);
        View cusviewGood = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewGood.setTag(4);
        View cusviewAppo = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewAppo.setTag(5);
        View cusviewHist = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewHist.setTag(6);
        View cusviewAddC = LayoutInflater.from(mActivity).inflate(R.layout.adapter_clock_button_nav_left,null);cusviewAddC.setTag(7);
        hRoll.removeAllViews();
        hRoll.addView(cusviewRoom);
        hRoll.addView(cusviewTech);
        hRoll.addView(cusviewProj);
        hRoll.addView(cusviewGood);
        hRoll.addView(cusviewAppo);
        hRoll.addView(cusviewHist);
        hRoll.addView(cusviewAddC);

        views.clear();
        views.add(cusviewRoom);
        views.add(cusviewTech);
        views.add(cusviewProj);
        views.add(cusviewGood);
        views.add(cusviewAppo);
        views.add(cusviewHist);
        views.add(cusviewAddC);

        final RoomFragment clock1Fragment = new RoomFragment();
        final TechFragment clock2Fragment = new TechFragment();
        final ProjFragment clock3Fragment = new ProjFragment();
        final GoodFragment clock4Fragment = new GoodFragment();
        final AppoFragment clock5Fragment = new AppoFragment();
        final HistFragment clock6Fragment = new HistFragment();
        final AddcFragment clock7Fragment = new AddcFragment();

        // init cusview
        for (final View v : views) {
            Button tv =  v.findViewById(R.id.des);
            switch ((int)v.getTag()){
                case 1:
                    tv.setText("房间");
                    break;
                case 2:
                    tv.setText("技师");
                    break;
                case 3:
                    tv.setText("项目");
                    break;
                case 4:
                    tv.setText("产品");
                    break;
                case 5:
                    tv.setText("预约");
                    break;
                case 6:
                    tv.setText("历史");
                    break;
                case 7:
                    tv.setText(" + ");
                    break;
            }

            View indicator =  v.findViewById(R.id.indicator);
            tv.setTag(indicator);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    currTag(button);
                    switch ((int)v.getTag()){
                        case 1:
                            FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock1Fragment,R.id.frame);
                            break;
                        case 2:
                            FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock2Fragment,R.id.frame);
                            break;
                        case 3:
                            FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock3Fragment,R.id.frame);
                            break;
                        case 4:
                            FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock4Fragment,R.id.frame);
                            break;
                        case 5:
                            FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock5Fragment,R.id.frame);
                            break;
                        case 6:
                            FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock6Fragment,R.id.frame);
                            break;
                        case 7:
                            View contentView = LayoutInflater.from(mActivity).inflate(R.layout.pop_clock_arrange,null);
                            //处理popWindow 显示内容
                            //handleLogic(contentView);
                            //创建并显示popWindow
                            new CustomPopWindow.PopupWindowBuilder(mActivity)
                                    .setView(contentView)
                                    .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                                    .setBgDarkAlpha(0.8f) // 控制亮度
                                    .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                                        @Override
                                        public void onDismiss() {
                                            Log.e("TAG","onDismiss");
                                        }
                                    })
                                    .create()

                                    //.showAsDropDown(mButton5,0,20);
                                    .showAtLocation(MainActivity.instance.getMainBoard(), Gravity.CENTER,0,0);
                            //FragmentUtils.replace(getActivity().getSupportFragmentManager(),clock7Fragment,R.id.frame);
                            break;
                    }
                }
            });
        }

        currTag(cusviewRoom.findViewById(R.id.des));
        FragmentUtils.add(getActivity().getSupportFragmentManager(),clock1Fragment,R.id.frame);
    }

    private void resetCusview(){
        for(View v:views){
            Button tv =  v.findViewById(R.id.des);
            View indicator =  v.findViewById(R.id.indicator);
            tv.setTextColor(getResources().getColor(R.color.black));
            indicator.setVisibility(View.GONE);
        }
    }
    private void currTag(View v){
        resetCusview();
        ((Button)v).setTextColor(getResources().getColor(R.color.colorAccent));
        ((View)v.getTag()).setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(Bundle arguments) {

    }
}
