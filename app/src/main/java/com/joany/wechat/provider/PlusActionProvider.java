package com.joany.wechat.provider;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.joany.wechat.R;

/**
 * Created by joany on 2016/7/29.
 */
public class PlusActionProvider extends ActionProvider{

    private Context context;

    public PlusActionProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        subMenu.add(context.getResources().getString(R.string.menu_group_chat))
                .setIcon(R.mipmap.icon_menu_group)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return true;
                    }
                });
        subMenu.add(context.getResources().getString(R.string.menu_add_friend))
                .setIcon(R.mipmap.icon_menu_addfriend)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return true;
                    }
                });
        subMenu.add(context.getResources().getString(R.string.menu_scan))
                .setIcon(R.mipmap.icon_menu_sao)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return true;
                    }
                });
    }

    @Override
    public boolean hasSubMenu() {
        //返回true点击才能展开
        return true;
    }
}
