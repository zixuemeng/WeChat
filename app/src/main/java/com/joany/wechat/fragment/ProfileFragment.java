package com.joany.wechat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joany.wechat.R;
import com.joany.wechat.utils.Utils;

/**
 * Created by joany on 2016/7/28.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    private ImageView qrcodeImg;
    private TextView photoTv;
    private TextView collectTv;
    private TextView moneyTv;
    private TextView faceTv;
    private TextView settingsTv;
    private RelativeLayout rll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initData(view);
        initView();
        return view;
    }

    private void initData(View view) {
        qrcodeImg = (ImageView) view.findViewById(R.id.qrcodeImg);
        photoTv = (TextView) view.findViewById(R.id.photoTv);
        collectTv = (TextView) view.findViewById(R.id.collectTv);
        moneyTv = (TextView) view.findViewById(R.id.money);
        faceTv = (TextView) view.findViewById(R.id.faceTv);
        settingsTv = (TextView) view.findViewById(R.id.settings);
        rll = (RelativeLayout) view.findViewById(R.id.rll);
    }

    private void initView(){
        qrcodeImg.setOnClickListener(this);
        photoTv.setOnClickListener(this);
        collectTv.setOnClickListener(this);
        moneyTv.setOnClickListener(this);
        faceTv.setOnClickListener(this);
        settingsTv.setOnClickListener(this);
        rll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qrcodeImg:
                Utils.showDialog(getContext());
                break;
            case R.id.rll:
                Utils.showShortToast(getContext(),"进入个人资料");
                break;
            case R.id.photoTv:
                Utils.showShortToast(getContext(),"进入相册");
                break;
            case R.id.collectTv:
                Utils.showShortToast(getContext(),"进入收藏");
                break;
            case R.id.money:
                Utils.showShortToast(getContext(),"进入钱包");
                break;
            case R.id.faceTv:
                Utils.showShortToast(getContext(),"进入表情");
                break;
            case R.id.settings:
                Utils.showShortToast(getContext(),"进入设置");
                break;
            default:
                break;
        }
    }
}
