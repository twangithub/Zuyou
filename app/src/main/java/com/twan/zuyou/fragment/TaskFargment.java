package com.twan.zuyou.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

import com.twan.zuyou.R;
import com.twan.zuyou.app.BaseFragment;
import com.twan.zuyou.ui.MainActivity;
import com.twan.zuyou.ui.SecondeActivity;

import org.xutils.router.Router;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by twan on 2017/10/26.
 */
@ContentView(R.layout.fragment_task)
public class TaskFargment extends BaseFragment {
    @ViewInject(R.id.button11) Button button;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(mActivity).to(SecondeActivity.class).launch();
            }
        });
    }
}
