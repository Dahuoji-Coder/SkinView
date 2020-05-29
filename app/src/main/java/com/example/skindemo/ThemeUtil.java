package com.example.skindemo;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by yang on 2020/5/29.
 */

public class ThemeUtil {

    public static final int NORMAL = 10000;
    public static final int VIP = 10001;
    public static final int DARK = 10002;

    private static int theme;
    public static final String THEME_VIP_ = "theme_vip_";
    public static final String THEME_DARK_ = "theme_dark_";

    public static void setTheme(int theme) {
        ThemeUtil.theme = theme;
    }

    public static int getResourceIdByTheme(Context context, int resId) {
        if (resId != 0) {
            String theme_;
            if (theme == VIP) {
                theme_ = THEME_VIP_;
            } else if (theme == DARK) {
                theme_ = THEME_DARK_;
            } else {
                return resId;
            }
            String resourceName = context.getResources().getResourceName(resId);
            if (!TextUtils.isEmpty(resourceName)) {
                if (resourceName.contains(":")) {
                    resourceName = resourceName.split(":")[1];
                    if (resourceName.contains("/")) {
                        String imageType = resourceName.split("/")[0];
                        String imageName = resourceName.split("/")[1];
                        if (!imageName.startsWith(theme_)) {
                            int vipResourceId = context.getResources().getIdentifier(theme_ + imageName, imageType, context.getPackageName());
                            if (vipResourceId != 0) {
                                resId = vipResourceId;
                            }
                        }
                    }
                }
            }
        }

        return resId;
    }
}
