package com.joany.wechat.utils;

import com.joany.wechat.bean.UserInfo;

import java.util.Comparator;

/**
 * Created by joany on 2016/8/1.
 */
public class PinYinComparator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        UserInfo userInfo1 = (UserInfo) o;
        UserInfo userInfo2 = (UserInfo) t1;
        String divider1 = "";
        String divider2="";
        if(userInfo1 != null && userInfo1.getUserName() != null) {
            divider1 = PinYinUtil.convertToFirstSpell(userInfo1.getUserName()).substring(0,1)
                    .toUpperCase();
        }

        if(userInfo2 != null && userInfo2.getUserName() != null) {
            divider2 = PinYinUtil.convertToFirstSpell(userInfo2.getUserName()).substring(0,1)
                    .toUpperCase();
        }
        return divider1.compareTo(divider2);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}
