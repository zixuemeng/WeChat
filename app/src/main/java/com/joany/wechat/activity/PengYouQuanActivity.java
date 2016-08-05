package com.joany.wechat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joany.wechat.R;
import com.joany.wechat.adapter.PengYouQuanAdapter;
import com.joany.wechat.utils.Utils;

/**
 * Created by joany on 2016/8/2.
 */
public class PengYouQuanActivity extends Activity implements View.OnClickListener{

    private ImageView backImg;
    private TextView titleTv;
    private ImageView rightImg;
    private ListView listView;
    private View headerView;
    private TextView headerBackgroundTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pengyouquan);
        initData();
        initView();
    }

    private void initData(){
        backImg = (ImageView) findViewById(R.id.backImg);
        //此头文件可能共用，title和rightImg在代码中赋值
        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText(getResources().getString(R.string.fragment_discovery_pengyouquan));
        rightImg = (ImageView) findViewById(R.id.rightImg);
        rightImg.setImageResource(R.mipmap.icon_talk);

        listView = (ListView) findViewById(R.id.listview);
        headerView = LayoutInflater.from(this).inflate(R.layout.pengyouquan_header,null);
        listView.addHeaderView(headerView);
        listView.setAdapter(new PengYouQuanAdapter(this));

        headerBackgroundTv = (TextView) findViewById(R.id.backgroundTv);
    }

    private void initView(){
        backImg.setOnClickListener(this);
        rightImg.setOnClickListener(this);
        rightImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Utils.utilStartActivity(PengYouQuanActivity.this, ShareActivity.class);
                return true;
            }
        });
        headerBackgroundTv.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backImg:
                Utils.finish(this);
                break;
            case R.id.rightImg:
                Utils.showLongToast(getApplicationContext(),"选择照片/小视频");
                break;
            case R.id.backgroundTv:
                headerBackgroundTv.setBackground(getResources().getDrawable(R.mipmap.ic_5));
                headerBackgroundTv.setText(null);
                break;
            default:
                break;
        }
    }
}
