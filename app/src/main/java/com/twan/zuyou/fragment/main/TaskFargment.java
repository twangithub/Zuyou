package com.twan.zuyou.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;import android.view.View;
import android.view.ViewGroup;import android.widget.Button;

import com.twan.mylibary.router.Router;
import com.twan.zuyou.R;
import com.twan.zuyou.api.Api;import com.twan.zuyou.api.Result;import com.twan.zuyou.app.BaseFragment;
import com.twan.zuyou.entity.Room;import com.twan.zuyou.ui.SecondeActivity;import com.twan.zuyou.util.LogUtil;
import java.util.List;import butterknife.BindView;import io.reactivex.functions.Consumer;import retrofit2.Call;import retrofit2.Callback;import retrofit2.Response;


/**
 * Created by twan on 2017/10/26.
 */
public class TaskFargment extends BaseFragment {
    @BindView(R.id.button11) Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(mActivity).to(SecondeActivity.class).launch();
            }
        });
    }

    @Override
    protected void initData(Bundle arguments) {


    }
}
