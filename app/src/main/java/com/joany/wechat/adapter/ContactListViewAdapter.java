package com.joany.wechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.joany.wechat.R;
import com.joany.wechat.bean.UserInfo;
import com.joany.wechat.utils.PinYinComparator;
import com.joany.wechat.utils.PinYinUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by joany on 2016/7/29.
 */
public class ContactListViewAdapter extends BaseAdapter implements SectionIndexer{

    private Context context;
    private List<UserInfo> list;

    public ContactListViewAdapter(Context context,List<UserInfo> list){
        this.context = context;
        this.list = list;
        Collections.sort(list,new PinYinComparator());
    }

    @Override
    public int getCount() {
        return list!= null ? list.size():0;
    }

    @Override
    public Object getItem(int i) {
        return list!= null ? list.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        UserInfo userInfo = list.get(i);
        ViewHolder viewHolder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.contact_listview_item,
                    viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.titleDividerTv = (TextView) view.findViewById(R.id.titleDividerTv);
            viewHolder.contactItemIv = (ImageView) view.findViewById(R.id.contactItemIv);
            viewHolder.contactItemNickTv = (TextView) view.findViewById(R.id.contactItemNickTv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String divider = getDivider(userInfo);

        if(i == 0) {
            viewHolder.titleDividerTv.setVisibility(View.VISIBLE);
            viewHolder.titleDividerTv.setText(divider);
        } else {
            UserInfo preUserInfo = list.get(i-1);
            String preDivider = getDivider(preUserInfo);
            if(divider.equals(preDivider)) {
                viewHolder.titleDividerTv.setVisibility(View.GONE);
            } else {
                viewHolder.titleDividerTv.setVisibility(View.VISIBLE);
                viewHolder.titleDividerTv.setText(divider);
            }
        }

        viewHolder.contactItemIv.setImageDrawable(context.getResources().getDrawable(userInfo.getHeadDrawableId()));
        viewHolder.contactItemNickTv.setText(userInfo.getUserName());
        return view;
    }

    private String getDivider(UserInfo userInfo) {
        //TODO:获取拼音首字母
        return PinYinUtil.convertToFirstSpell(userInfo.getUserName()).substring(0,1).toUpperCase();
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int position) {
        for(int i = 0; i < list.size(); i++) {
            UserInfo userInfo = list.get(i);
            String pinYinFirstChar = PinYinUtil.convertToFirstSpell(userInfo.getUserName())
                    .substring(0, 1);
            char firstChar = pinYinFirstChar.toUpperCase().charAt(0);
            if(firstChar == position) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView titleDividerTv;
        ImageView contactItemIv;
        TextView contactItemNickTv;
    }
}
