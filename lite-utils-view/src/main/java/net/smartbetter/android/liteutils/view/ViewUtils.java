package net.smartbetter.android.liteutils.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class ViewUtils {
    /**
     * 单例模式(静态内部类的方式)
     */
    private ViewUtils() {
    }
    public static ViewUtils getInstance() {
        return SingletonHolder.instance;
    }
    private static class SingletonHolder {
        private static final ViewUtils instance = new ViewUtils();
    }

    /**
     * 把自身从父View中移除
     *
     * @param view
     */
    public void removeSelfFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }
        }
    }

    /**
     * 判断触点是否落在该View上
     *
     * @param ev
     * @param v
     * @return
     */
    public boolean isTouchInView(MotionEvent ev, View v) {
        int[] vLoc = new int[2];
        v.getLocationOnScreen(vLoc);
        float motionX = ev.getRawX();
        float motionY = ev.getRawY();
        return motionX >= vLoc[0] && motionX <= (vLoc[0] + v.getWidth())
                && motionY >= vLoc[1] && motionY <= (vLoc[1] + v.getHeight());
    }

}