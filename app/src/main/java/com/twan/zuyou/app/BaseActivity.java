package com.twan.zuyou.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.twan.swipebacklayout.app.SwipeBackActivity;

import org.xutils.x;


/**
 * Created by wyouflf on 15/11/4.
 */
public abstract class BaseActivity extends SwipeBackActivity {
    protected Toolbar mToolbar;
    protected Activity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        x.view().inject(this);
        mContext = this;
        App.getInstance().addActivity(this);
        initEventAndData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();

}
