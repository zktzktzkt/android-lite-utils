package net.smartbetter.android.liteutils.common;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * 吐司相关工具类
 */
public class ToastUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private ToastUtils() {
    }
    public static ToastUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final ToastUtils instance = new ToastUtils();
    }

    private static Toast mToast;
    private static WeakReference<View> mViewWeakReference;//弱引用

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 消息
     */
    public void showShort(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 消息
     */
    public void showLong(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 显示吐司
     *
     * @param context  上下文
     * @param text     文本
     * @param duration 显示时长
     */
    private void show(Context context, CharSequence text, int duration) {
        cancel();
        boolean isCustom = false;
        if (mViewWeakReference != null) {
            final View view = mViewWeakReference.get();
            if (view != null) {
                mToast = new Toast(context);
                mToast.setView(view);
                mToast.setDuration(duration);
                isCustom = true;
            }
        }
        if (!isCustom) {
            mToast = Toast.makeText(context, text, duration);
        }
        mToast.show();
    }

    /**
     * 取消吐司显示
     */
    private void cancel() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}