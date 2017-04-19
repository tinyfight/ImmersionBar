# ImmersionBar -- android 4.4以上沉浸式实现 #

## 使用 ##
该项目已经上传到jCenter仓库中，使用的时候可以直接使用compile依赖，用法如下

	compile 'com.gyf.barlibrary:barlibrary:1.2.1'


## 下载demo ##
[点我](https://github.com/gyf-dev/ImmersionBar/blob/master/apk/sample-debug.apk) 
    
## 用法 BarManager.xxx()方法 ##
#### ① 以下方法是自定义状态栏和导航栏颜色，任选其一使用，style风格使用NoActionBar效果更佳 ####
- public static void setBarColor(Activity activity)
 
	Activity非全屏,默认状态栏为透明色，导航栏为黑色

- public static void setBarColor(Activity activity,boolean flag)

	flag为true状态栏和导航栏全为透明色，flag为false同public static void immersionBar(Activity activity)

- public static void setBarColor(Activity activity, BarType barType,int BarColor,boolean flag)

	自定义状态栏和导航栏其中一个的颜色或者全部为同一种颜色

- public static void setBarColor(Activity activity, int StatusBarColor, int NavigationBarColor,boolean flag)

	自定义状态栏和导航栏颜色
	
	
> flag说明：
> 
true--Activity全屏显示，但导航栏不会被隐藏，导航栏依然可见，Activity底部布局部分会被导航栏遮住。
>
 false--Activity非全屏显示，但导航栏不会被隐藏，导航栏依然可见，Activity底部布局不会被导航栏遮住。	
	
#### ② 状态栏和导航栏其它方法 ####
- public static void hideBar(Activity activity, BarHide barHide)

	隐藏或显示状态栏和导航栏。 状态栏和导航栏的颜色不起作用，都是透明色，以最后一次调用为准
	
- public static boolean hasNavigationBar(Activity activity)
 
    判断是否存在导航栏
    
- public static int getNavigationBarHeight(Activity activity)
 
    获得导航栏的高度
 
- public static int getNavigationBarWidth(Activity activity)
 
    获得导航栏的宽度
    
- public static boolean isNavigationAtBottom(Activity activity)
 
    判断导航栏是否在底部
    
- public static int getStatusBarHeight(Activity activity)
 
    或得状态栏的高度
    
- public static int getActionBarHeight(Activity activity)
 
    或得ActionBar得高度

- public static void setStatusBarDarkFont(Activity activity, boolean dark)

    设置状态栏字体颜色，dark为true为深色，否则为亮色（android6.0以上或者miuiv6以上或者flymeOS）

## 效果图 ##
![image](https://github.com/gyf-dev/ImmersionBar/blob/master/screenshots/Screenshot0.png)
![image](https://github.com/gyf-dev/ImmersionBar/blob/master/screenshots/Screenshot1.png)
## 参考 ##
- Android 4.4使用的是github上第三方库实现，参考地址：https://github.com/jgilfelt/SystemBarTint
- Android 5.0以上使用自带Api实现
