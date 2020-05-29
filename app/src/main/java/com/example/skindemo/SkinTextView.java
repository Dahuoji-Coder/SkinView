package com.example.skindemo;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by 10732 on 2018/5/28.
 */

public class SkinTextView extends AppCompatTextView {

    private Context context;
    private int currentBackgroundResourceId,
            currentDrawableTopId,
            currentDrawableRightId,
            currentDrawableLeftId,
            currentTextColorId;

    public SkinTextView(Context context) {
        super(context);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        currentBackgroundResourceId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "background", 0);
        currentDrawableTopId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableTop", 0);
        currentDrawableRightId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableRight", 0);
        currentDrawableLeftId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableLeft", 0);
        currentTextColorId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "textColor", 0);

        updateTheme();
    }

    @Override
    public void setBackgroundResource(int resId) {
        resId = ThemeUtil.getResourceIdByTheme(context, resId);
        super.setBackgroundResource(resId);
    }

    public void setCompoundDrawablesById(int start, int top, int end, int bottom) {
        currentDrawableTopId = top;
        currentDrawableLeftId = start;
        currentDrawableRightId = end;
        top = ThemeUtil.getResourceIdByTheme(context, top);
        start = ThemeUtil.getResourceIdByTheme(context, start);
        end = ThemeUtil.getResourceIdByTheme(context, end);
        setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    public void setTextColorById(int colorId) {
        currentTextColorId = colorId;
        colorId = ThemeUtil.getResourceIdByTheme(context, colorId);
        if (colorId != 0) {
            super.setTextColor(getResources().getColor(colorId));
        }
    }

    public void updateTheme() {
        int backgroundResourceId = currentBackgroundResourceId;
        if (backgroundResourceId != 0) {
            backgroundResourceId = ThemeUtil.getResourceIdByTheme(context, backgroundResourceId);
            setBackgroundResource(backgroundResourceId);
        }
        int drawableTopId = currentDrawableTopId;
        int drawableRightId = currentDrawableRightId;
        int drawableLeftId = currentDrawableLeftId;
        if (drawableTopId != 0) {
            drawableTopId = ThemeUtil.getResourceIdByTheme(context, drawableTopId);
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, drawableTopId, 0, 0);
        } else if (drawableRightId != 0) {
            drawableRightId = ThemeUtil.getResourceIdByTheme(context, drawableRightId);
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableRightId, 0);
        } else if (drawableLeftId != 0) {
            drawableLeftId = ThemeUtil.getResourceIdByTheme(context, drawableLeftId);
            setCompoundDrawablesRelativeWithIntrinsicBounds(drawableLeftId, 0, 0, 0);
        }

        int textColorId = ThemeUtil.getResourceIdByTheme(context, currentTextColorId);
        if (textColorId != 0) {
            setTextColor(getResources().getColor(textColorId));
        }
    }
}