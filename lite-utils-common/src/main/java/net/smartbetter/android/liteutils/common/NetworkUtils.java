package net.smartbetter.android.liteutils.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class NetworkUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private NetworkUtils() {
    }
    public static NetworkUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final NetworkUtils instance = new NetworkUtils();
    }

    private static final int ACTION_WIFI_SETTINGS = 0;
    private static final int ACTION_DATA_ROAMING_SETTINGS = 1;

    /**
     * 判断网络是否可用，需要加上访问网络状态的权限
     *
     * @param context
     * @return
     */
    public boolean isNetworkAvaiable(Context context) {
        ConnectivityManager connectivity = getConnectivityManager(context);
        if (connectivity == null) {
            return false;
        }
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public boolean isConnected(Context context) {
        ConnectivityManager connectivity = getConnectivityManager(context);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是WiFi网络
     *
     * @param context
     * @return
     */
    public boolean isWifiConn(Context context) {
        boolean isCommected = isConnected(context);
        if (isCommected) {
            ConnectivityManager connectivity = getConnectivityManager(context);
            if (connectivity == null)
                return false;
            return connectivity.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * 获取网络连接管理
     *
     * @param context
     * @return
     */
    private ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 打开网络设置界面 整体
     *
     * @param activity
     */
    public void openSetting(Activity activity) {
        //整体
        activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
    }

    /**
     * 打开网络设置界面 WIFI/流量
     *
     * @param activity
     * @param i 0:WIFI/1:流量
     */
    public void openSetting(Activity activity, int i) {
        if (i == ACTION_WIFI_SETTINGS) {
            //WIFI
            activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } else if (i == ACTION_DATA_ROAMING_SETTINGS) {
            //流量
            activity.startActivity(new Intent(
                    android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));
        }
    }

}
