package net.smartbetter.android.liteutils.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * 广播相关工具类
 */
public class BroadcastUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private BroadcastUtils() {
    }
    public static BroadcastUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final BroadcastUtils instance = new BroadcastUtils();
    }

    /**
     * 发送系统广播
     */
    public void sendSystemBroadcast(Context context, String action, Bundle bundle) {
        Intent intent = new Intent(action);
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * 注册系统广播
     */
    public void registerSystemBroadcast(Context context, BroadcastReceiver broadcastReceiver, String[] actionArray) {
        //动态注册广播接收器
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        for (String string : actionArray) {
            intentFilter.addAction(string);
        }
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * 注销系统广播
     */
    public void unRegisterSystemBroadcast(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        broadcastManager.unregisterReceiver(broadcastReceiver);
    }

}
