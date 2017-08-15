package com.example.liteutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.smartbetter.android.liteutils.common.AppUtils;
import net.smartbetter.android.liteutils.common.LogUtils;
import net.smartbetter.android.liteutils.common.SDCardUtils;
import net.smartbetter.android.liteutils.common.ToastUtils;
import net.smartbetter.android.liteutils.view.ScreenUtils;

public class MainActivity extends AppCompatActivity {
    private static final String[] strs = new String[]{
            "show short toast",
            "get package name",
            "get sdcard all size",
            "get screen height"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.i("onCreate");

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switchActivity(position);
            }
        });
    }

    private void switchActivity(int position) {
        switch (position) {
            case 0:
                ToastUtils.getInstance().showShort(this, "hello");
                break;
            case 1:
                ToastUtils.getInstance().showLong(this,
                        AppUtils.getInstance().getPackageName(this));
                break;
            case 2:
                ToastUtils.getInstance().showLong(this,
                        ""+SDCardUtils.getInstance().getSDCardAllSize());
                break;
            case 3:
                ToastUtils.getInstance().showLong(this,
                        ""+ ScreenUtils.getInstance().getScreenHeight(this));
                break;
            default:
                break;
        }
    }
}
