package com.twan.zuyou.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jaeger.library.StatusBarUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.twan.zuyou.R;
import com.twan.zuyou.adapter.IndicateAdapter;
import com.twan.zuyou.app.App;
import com.twan.zuyou.app.BaseActivity;
import com.twan.zuyou.app.Constants;
import com.twan.zuyou.fragment.main.CashFragment;
import com.twan.zuyou.fragment.main.CircleFragment;
import com.twan.zuyou.fragment.main.ClockFragment;
import com.twan.zuyou.fragment.main.MyFragment;
import com.twan.zuyou.fragment.main.TaskFargment;import com.twan.zuyou.widget.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;import butterknife.BindView;import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottomBar) BottomBar bottomBar;
    @BindView(R.id.fab_action_1) FloatingActionButton fab_action_1;
    @BindView(R.id.fab_action_2) FloatingActionButton fab_action_2;
    @BindView(R.id.mainboard) View mainboard;
    @BindView(R.id.multiple_actions) FloatingActionsMenu fab;
    List<Fragment> views = new ArrayList<>();
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
        setSwipeBackEnable(false);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        StatusBarUtil.setColor(MainActivity.this, Color.parseColor(Constants.FR1_COLOR));

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
                        currTab(0);
                        viewpager.setCurrentItem(0, true);
                        break;
                    case R.id.tab_cash:
                        currTab(1);
                        viewpager.setCurrentItem(1, true);
                        break;
                    case R.id.tab_clock:
                        currTab(2);
                        viewpager.setCurrentItem(2, true);
                        break;
                    case R.id.tab_circle:
                        currTab(3);
                        viewpager.setCurrentItem(3, true);
                        break;
                    case R.id.tab_my:
                        currTab(4);
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

    @OnClick(R.id.fab_action_1)
    public void onFabActionNetClick(View view){
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_clock_arrange,null);
        //处理popWindow 显示内容
        //handleLogic(contentView);
        //创建并显示popWindow
         new CustomPopWindow.PopupWindowBuilder(this)
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
                .showAtLocation(mainboard, Gravity.CENTER,0,0);
    }

    private void currTab(int itemId){
        if (itemId == 2){
            //fab_action_1=(FloatingActionButton)findViewById(R.id.fab_action_1);
            //fab_action_2=(FloatingActionButton)findViewById(R.id.fab_action_2);
            //fab_action_1.setVisibility(View.GONE);
            //fab_action_2.setVisibility(View.GONE);
        }
    }

    public View getMainBoard(){
        return mainboard;
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
