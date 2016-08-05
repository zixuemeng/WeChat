package com.joany.wechat.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import com.joany.wechat.R;
import com.joany.wechat.adapter.ViewPagerAdapter;
import com.joany.wechat.fragment.ContactFragment;
import com.joany.wechat.fragment.FindFragment;
import com.joany.wechat.fragment.ProfileFragment;
import com.joany.wechat.fragment.MessageFragment;
import com.joany.wechat.view.IconTextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private IconTextView messageItv;
    private IconTextView contactItv;
    private IconTextView findItv;
    private IconTextView profileItv;
    private List<IconTextView> tabContainer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOverflowShowingAlways();
        initData();
        initView();
    }

    private void initData() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        messageItv = (IconTextView) findViewById(R.id.msgItv);
        contactItv = (IconTextView) findViewById(R.id.contactItv);
        findItv = (IconTextView) findViewById(R.id.findItv);
        profileItv = (IconTextView) findViewById(R.id.profileItv);
    }

    private void initView() {
        list.add(new MessageFragment());
        list.add(new ContactFragment());
        list.add(new FindFragment());
        list.add(new ProfileFragment());

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                list, getApplicationContext()));
        viewPager.setOnPageChangeListener(this);

        messageItv.setOnClickListener(this);
        contactItv.setOnClickListener(this);
        findItv.setOnClickListener(this);
        profileItv.setOnClickListener(this);

        tabContainer.add(messageItv);
        tabContainer.add(contactItv);
        tabContainer.add(findItv);
        tabContainer.add(profileItv);

        messageItv.setViewAlpha(255);

        //actionbar上不显示应用图标
        ActionBar mActionBar = getActionBar();
        if(mActionBar != null) {
            mActionBar.setDisplayShowHomeEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //让隐藏在overflow中的action按钮的图标显示出来
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    //屏蔽物理Menu键，在有物理Menu键的手机上，overflow按钮不会显示出来
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.msgItv:
                resetTabs();
                messageItv.setViewAlpha(255);
                viewPager.setCurrentItem(0,false);//smoothscroll:false
                break;
            case R.id.contactItv:
                resetTabs();
                contactItv.setViewAlpha(255);
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.findItv:
                resetTabs();
                findItv.setViewAlpha(255);
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.profileItv:
                resetTabs();
                profileItv.setViewAlpha(255);
                viewPager.setCurrentItem(3,false);
                break;
        }
    }

    private void resetTabs() {
        messageItv.setViewAlpha(0);
        contactItv.setViewAlpha(0);
        findItv.setViewAlpha(0);
        profileItv.setViewAlpha(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    //dragging,setting,idle,0-1:position:0,offset:0.0-1.0,1-0:position:0,offset:1.0-0.0
        if(positionOffset > 0) {
            IconTextView left = tabContainer.get(position);
            IconTextView right = tabContainer.get(position + 1);
            left.setViewAlpha((int) (255*(1-positionOffset)));
            right.setViewAlpha((int) (255*positionOffset));
        }
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
