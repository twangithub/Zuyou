package com.twan.zuyou.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.haha.perflib.Main;
import com.twan.zuyou.R;
import com.twan.zuyou.adapter.IndicateAdapter;
import com.twan.zuyou.app.App;
import com.twan.zuyou.app.BaseActivity;
import com.twan.zuyou.app.Constants;
import com.twan.zuyou.fragment.CashFragment;
import com.twan.zuyou.fragment.CircleFragment;
import com.twan.zuyou.fragment.ClockFragment;
import com.twan.zuyou.fragment.MyFragment;
import com.twan.zuyou.fragment.TaskFargment;

import org.xutils.router.Router;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewInject(R.id.bottomBar) BottomBar bottomBar;
    @ViewInject(R.id.fab_action_net) FloatingActionButton fab_action_net;
    List<Fragment> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initEventAndData() {
        final ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        CashFragment cashFragment = new CashFragment();
        TaskFargment taskFargment = new TaskFargment();
        ClockFragment clockFragment = new ClockFragment();
        MyFragment myFragment = new MyFragment();
        CircleFragment circleFragment = new CircleFragment();
        views.clear();
        views.add(taskFargment);
        views.add(cashFragment);
        views.add(clockFragment);
        views.add(circleFragment);
        views.add(myFragment);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_task:
                        viewpager.setCurrentItem(0, true);
                        break;
                    case R.id.tab_cash:
                        viewpager.setCurrentItem(1, true);
                        break;
                    case R.id.tab_clock:
                        viewpager.setCurrentItem(2, true);
                        break;
                    case R.id.tab_circle:
                        viewpager.setCurrentItem(3, true);
                        break;
                    case R.id.tab_my:
                        viewpager.setCurrentItem(4, true);
                        break;
                }
            }
        });

        IndicateAdapter myFragmentAdapter = new IndicateAdapter(getSupportFragmentManager(), views);
        viewpager.setAdapter(myFragmentAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                if (bottomBar != null) bottomBar.selectTabAtPosition(pos, true);
                if (pos == 0) {
                    StatusBarUtil.setColor(MainActivity.this, Color.parseColor(Constants.FR1_COLOR));
                } else if (pos == 1) {
                    StatusBarUtil.setColor(MainActivity.this, Color.parseColor(Constants.FR2_COLOR));
                } else if (pos == 2) {
                    StatusBarUtil.setColor(MainActivity.this, Color.parseColor(Constants.FR3_COLOR));
                }else if (pos == 3) {
                    StatusBarUtil.setColor(MainActivity.this, Color.parseColor(Constants.FR1_COLOR));
                } else if (pos == 4) {
                    StatusBarUtil.setColor(MainActivity.this, Color.parseColor(Constants.FR2_COLOR));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            //showExitDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showExitDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出GeekNews吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }

}
