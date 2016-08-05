package com.joany.wechat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.joany.wechat.R;
import com.joany.wechat.adapter.ContactListViewAdapter;
import com.joany.wechat.bean.UserInfo;
import com.joany.wechat.utils.Utils;
import com.joany.wechat.view.SideBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joany on 2016/7/28.
 */
public class ContactFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private SideBar sideBar;
    private View headerView;
    private List<UserInfo> userInfos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        listView = (ListView) view.findViewById(R.id.listview);
        sideBar = (SideBar) view.findViewById(R.id.sideBar);
        initData();
        initView();
        return view;
    }

    private void initData(){
        userInfos = getUserInfo();
    }

    private void initView() {
        sideBar.setListView(listView);
        headerView = getActivity().getLayoutInflater().inflate(R.layout.fragment_contact_header,null);
        listView.setAdapter(new ContactListViewAdapter(getContext(), userInfos));
        listView.addHeaderView(headerView);
        listView.setOnItemClickListener(this);
        headerView.findViewById(R.id.friend).setOnClickListener(this);
        headerView.findViewById(R.id.group).setOnClickListener(this);
        headerView.findViewById(R.id.subscriber).setOnClickListener(this);
    }

    private List<UserInfo> getUserInfo(){
        //TODO:从数据库中获取好友信息
        List<UserInfo> list = new ArrayList<>();
        UserInfo mUserInfo = new UserInfo();
        mUserInfo.setUserName("你");
        mUserInfo.setHeadDrawableId(R.mipmap.ic_4);
        list.add(mUserInfo);
        UserInfo mUserInfo2 = new UserInfo();
        mUserInfo2.setUserName("wo");
        mUserInfo2.setHeadDrawableId(R.mipmap.ic_1);
        list.add(mUserInfo2);
        UserInfo mUserInfo3 = new UserInfo();
        mUserInfo3.setUserName("Ta");
        mUserInfo3.setHeadDrawableId(R.mipmap.ic_2);
        list.add(mUserInfo3);
        UserInfo mUserInfo4 = new UserInfo();
        mUserInfo4.setUserName("Nick");
        mUserInfo4.setHeadDrawableId(R.mipmap.ic_3);
        list.add(mUserInfo4);
        UserInfo mUserInfo5 = new UserInfo();
        mUserInfo5.setUserName("Z");
        mUserInfo5.setHeadDrawableId(R.mipmap.ic_4);
        list.add(mUserInfo5);
        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.friend:
                //TODO:enter new friend
                Utils.showShortToast(getContext(),"新的朋友");
                break;
            case R.id.group:
                //TODO:enter group chat
                Utils.showShortToast(getContext(),"群聊");
                break;
            case R.id.subscriber:
                //TODO:enter subscriber
                Utils.showShortToast(getContext(),"公众号");
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
