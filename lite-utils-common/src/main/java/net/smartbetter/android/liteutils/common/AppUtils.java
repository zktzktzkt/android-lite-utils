package net.smartbetter.android.liteutils.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private AppUtils() {
    }
    public static AppUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final AppUtils instance = new AppUtils();
    }

    /**
     * 获取app名称
     *
     * @param context
     * @return
     */
    public String getAppName(Context context) {
        PackageInfo info = getPackageInfo(context);
        int labelRes = info.applicationInfo.labelRes;
        return context.getResources().getString(labelRes);
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public String getPackageName(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info.packageName;
    }

    /**
     * 获取app版本名称
     *
     * @param context
     * @return
     */
    public String getVersionName(Context context) {
        PackageInfo info = getPackageInfo(context);
        return info.versionName;
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public int getVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo.versionCode;
    }

    private PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}