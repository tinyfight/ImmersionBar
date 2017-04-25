package com.gyf.barlibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.barlibrary.SystemBarTintManager.SystemBarConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * android 4.4以上bar 管理
 * Created by gyf on 2016/10/27.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class BarManager {

    /**
     * Immersion bar.
     * Activity非全屏,isFullScreen默认为false,默认状态栏为透明色，导航栏为黑色
     *
     * @param activity the activity
     */
    public static void setBarColor(Activity activity) {
        setBarColor(activity, Color.TRANSPARENT, Color.BLACK, false);
    }

    /**
     * Immersion bar.
     * 当isFullScreen为true状态栏和导航栏全为透明色，当flase时同public static void immersionBar(Activity activity)
     *
     * @param activity     the activity
     * @param isFullScreen the isFullScreen  当true Activity全屏 ；当false Activity非全屏，默认导航栏颜色为黑色
     */
    public static void setBarColor(Activity activity, boolean isFullScreen) {
        if (isFullScreen)
            setBarColor(activity, Color.TRANSPARENT, Color.TRANSPARENT, true);
        else
            setBarColor(activity, Color.TRANSPARENT, Color.BLACK, false);
    }

    /**
     * Sets bar color.
     * 设置状态栏和导航栏颜色相同
     *
     * @param activity     the activity
     * @param BarColor     the bar color
     * @param isFullScreen the is full screen
     */
    public static void setBarColor(Activity activity, int BarColor, boolean isFullScreen) {
        setBarColor(activity, BarColor, BarColor, isFullScreen);
    }

    /**
     * Immersion bar.
     * 自定义状态栏和导航栏其中一个的颜色或者全部为同一种颜色
     *
     * @param activity     the activity
     * @param barType      the bar type   STATUS_BAR 状态栏 ； NAVIGATION_BAR 导航栏
     * @param BarColor     the bar color  自定义颜色
     * @param isFullScreen the isFullScreen 当true Activity全屏 ；当false Activity非全屏
     */
    public static void setBarColor(Activity activity, BarType barType, int BarColor, boolean isFullScreen) {
        switch (barType) {
            case STATUS_BAR:
                if (isFullScreen)
                    setBarColor(activity, BarColor, Color.TRANSPARENT, true);
                else
                    setBarColor(activity, BarColor, Color.BLACK, false);
                break;
            case NAVIGATION_BAR:
                setBarColor(activity, Color.TRANSPARENT, BarColor, isFullScreen);
                break;
            case ALL_BAR:
                setBarColor(activity, BarColor, BarColor, isFullScreen);
        }
    }

    /**
     * Immersion bar.
     * 自定义状态栏和导航栏颜色
     *
     * @param activity           the activity
     * @param StatusBarColor     the status bar color  状态栏颜色
     * @param NavigationBarColor the navigation bar color  导航栏颜色
     * @param isFullScreen       the isFullScreen  当true Activity全屏 ；当false Activity非全屏
     */
    public static void setBarColor(Activity activity, int StatusBarColor, int NavigationBarColor, boolean isFullScreen) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态栏遮住。
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;  //防止系统栏隐藏时内容区域大小发生变化
                if (isFullScreen) {
                    uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION; //Activity全屏显示，但导航栏不会被隐藏覆盖，导航栏依然可见，Activity底部布局部分会被导航栏遮住。
                }
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  //取消设置透明状态栏和导航栏
                window.getDecorView().setSystemUiVisibility(uiFlags);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  //需要设置这个 isFullScreen 才能调用 setStatusBarColor 来设置状态栏颜色
                window.setStatusBarColor(StatusBarColor);  //设置状态栏颜色
                window.setNavigationBarColor(NavigationBarColor);  //设置导航栏颜色
            } else {
                //透明状态栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (isFullScreen) {
                    //透明导航栏
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
                //创建状态栏的管理实例
                SystemBarTintManager tintManager = new SystemBarTintManager(activity);
                //激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                //设置状态栏颜色
                tintManager.setTintColor(StatusBarColor);
                //激活导航栏设置
                tintManager.setNavigationBarTintEnabled(true);
                //设置导航栏颜色
                tintManager.setNavigationBarTintColor(NavigationBarColor);
            }

        }
    }

    /**
     * Hide bar.
     * 隐藏或显示状态栏和导航栏。 状态栏和导航栏的颜色不起作用，都是透明色，以最后一次调用为准
     *
     * @param activity the activity
     * @param barHide  the bar hide
     */
    public static void hideBar(Activity activity, BarHide barHide) {
        int uiFlags = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            switch (barHide) {
                case FLAG_HIDE_BAR:
                    uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.INVISIBLE;
                    break;
                case FLAG_HIDE_STATUS_BAR:
                    uiFlags |= View.INVISIBLE;
                    break;
                case FLAG_HIDE_NAVIGATION_BAR:
                    uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                    break;
                case FLAG_SHOW_BAR:
                    uiFlags |= View.SYSTEM_UI_FLAG_VISIBLE;
                    break;
            }
        }
        uiFlags |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        activity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);
    }

    /**
     * Has navigtion bar boolean.
     * 判断是否存在导航栏
     *
     * @param activity the activity
     * @return the boolean
     */
    @TargetApi(14)
    public static boolean hasNavigationBar(Activity activity) {
        SystemBarConfig config = new SystemBarConfig(activity, true, true);
        return config.hasNavigtionBar();
    }

    /**
     * Gets navigation bar height.
     * 获得导航栏的高度
     *
     * @param activity the activity
     * @return the navigation bar height
     */
    @TargetApi(14)
    public static int getNavigationBarHeight(Activity activity) {
        SystemBarConfig config = new SystemBarConfig(activity, true, true);
        return config.getNavigationBarHeight();
    }

    /**
     * Gets navigation bar width.
     * 获得导航栏的宽度
     *
     * @param activity the activity
     * @return the navigation bar width
     */
    @TargetApi(14)
    public static int getNavigationBarWidth(Activity activity) {
        SystemBarConfig config = new SystemBarConfig(activity, true, true);
        return config.getNavigationBarWidth();
    }

    /**
     * Is navigation at bottom boolean.
     * 判断导航栏是否在底部
     *
     * @param activity the activity
     * @return the boolean
     */
    @TargetApi(14)
    public static boolean isNavigationAtBottom(Activity activity) {
        SystemBarConfig config = new SystemBarConfig(activity, true, true);
        return config.isNavigationAtBottom();
    }

    /**
     * Gets status bar height.
     * 或得状态栏的高度
     *
     * @param activity the activity
     * @return the status bar height
     */
    @TargetApi(14)
    public static int getStatusBarHeight(Activity activity) {
        SystemBarConfig config = new SystemBarConfig(activity, true, true);
        return config.getStatusBarHeight();
    }

    /**
     * Gets action bar height.
     * 或得ActionBar得高度
     *
     * @param activity the activity
     * @return the action bar height
     */
    @TargetApi(14)
    public static int getActionBarHeight(Activity activity) {
        SystemBarConfig config = new SystemBarConfig(activity, true, true);
        return config.getActionBarHeight();
    }

    /**
     * Sets status bar dark font.
     * 设置状态栏字体颜色，android6.0以上或者miuiv6以上或者flymeOS
     *
     * @param activity the activity
     * @param dark     the dark true为深色,否则为亮色
     */
    public static void setStatusBarDarkFont(Activity activity, boolean dark) {
        String MIUIVersion = OSUtils.MIUIVersion();
        if (!MIUIVersion.isEmpty()) {
            if (Integer.valueOf(MIUIVersion.substring(1)) >= 6) {
                MIUISetStatusBarLightMode(activity.getWindow(), dark);
            }
            return;
        }
        if (OSUtils.isFlymeOS()) {
            FlymeSetStatusBarLightMode(activity.getWindow(), dark);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && dark) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.
                    SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.
                    SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
