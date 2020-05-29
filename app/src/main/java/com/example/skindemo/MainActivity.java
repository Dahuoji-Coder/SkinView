package com.example.skindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SkinTextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleText);
    }

    private List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                //再次 调用本身（递归）
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

    public void btnNormal(View view) {
        titleText.setText("Theme = NORMAL");
        ThemeUtil.setTheme(ThemeUtil.NORMAL);
        changeSkin();
    }

    public void btnVIP(View view) {
        titleText.setText("Theme = VIP");
        ThemeUtil.setTheme(ThemeUtil.VIP);
        changeSkin();
    }

    public void btnDark(View view) {
        titleText.setText("Theme = DARK");
        ThemeUtil.setTheme(ThemeUtil.DARK);
        changeSkin();
    }

    private void changeSkin() {
        List<View> allChildViews = getAllChildViews(getWindow().getDecorView());
        for (int i = 0; i < allChildViews.size(); i++) {
            if (allChildViews.get(i) instanceof SkinTextView) {
                ((SkinTextView) allChildViews.get(i)).updateTheme();
            }
        }
    }
}
