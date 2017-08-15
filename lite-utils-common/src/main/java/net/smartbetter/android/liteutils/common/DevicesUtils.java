package net.smartbetter.android.liteutils.common;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class DevicesUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private DevicesUtils() {
    }
    public static DevicesUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final DevicesUtils instance = new DevicesUtils();
    }

    /**
     * 获取系统版本
     *
     * @return
     */
    public String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取设备型号
     *
     * @return
     */
    public String getDevicesModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取设备ID
     *
     * @return
     */
    public String getDevicesId(Context context) {
        return ((TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取日期
     *
     * @return
     */
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * 获取 SD 卡路径
     *
     * @param context
     * @param paramString
     * @return
     */
    public String getSDCacheDir(Context context, String paramString) {
        String absoultePath = "";
        if ("mounted".equals(Environment.getExternalStorageState()))
            absoultePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (absoultePath == null) {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null) {
                if (cacheDir.exists())
                    absoultePath = cacheDir.getPath();
            }
        }
        return absoultePath + File.separator + paramString;
    }

    /**
     * 获取设备mac地址
     *
     * @param context
     * @return
     */
    public String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        return mac;
    }

    /**
     * 获取设备IP
     *
     * @return
     */
    public String getDevicesIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en
                    .hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}