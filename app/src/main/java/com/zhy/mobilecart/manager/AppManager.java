package com.zhy.mobilecart.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * <p>
 *
 * @since v0.0.1
 */
public class AppManager {
    private static final String TAG = AppManager.class.getSimpleName();
    /**
     * activity栈
     */
    private static Stack<Activity> activityStack;
    /**
     * 业务相关activity栈
     */
    private static Stack<Activity> businessActivityStack;
    private static AppManager instance;



    private AppManager() {
    }

    /**
     * 添加业务相关activity到栈
     *
     * @param activity
     * @author liuyun
     * @since v0.1
     */
    public void addBusinessActivity(Activity activity) {
        if (businessActivityStack == null) {
            businessActivityStack = new Stack<Activity>();
        }
        businessActivityStack.add(activity);
    }


    /**
     * finish业务栈
     *
     * @author liuyun
     * @since v0.1
     */
    public void finishBusinessStack() {
        if (null == businessActivityStack) {
            return;
        }
        for (int i = 0, size = businessActivityStack.size(); i < size; i++) {
            if (null != businessActivityStack.get(i)) {
                businessActivityStack.get(i).finish();
            }
        }
        businessActivityStack.clear();
    }

    /**
     * 清空业务栈
     *
     * @author liuyun
     * @since v0.1
     */
    public void clearBussinessStack() {
        if (businessActivityStack != null) {
            businessActivityStack.clear();
            businessActivityStack = null;
        }
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);

    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public Activity findActivity(Class<?> cls) {
        Activity activity = null;
        for (Activity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    public void finishToActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            Activity activity = activityStack.lastElement();
            finishActivity(activity);
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (!(activity.getClass().equals(cls))) {
                finishActivity(activity);
            }
        }
    }

    public boolean isLastActivity() {
        if (null != activityStack && 1 == activityStack.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}

