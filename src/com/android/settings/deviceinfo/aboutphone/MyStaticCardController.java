package com.android.settings.deviceinfo.aboutphone;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import android.graphics.Color;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.cardview.widget.CardView;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import android.content.res.Configuration;

import com.android.settings.R; // 确保导入你自己的 R 文件
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.LayoutPreference;

public class MyStaticCardController extends BasePreferenceController {

    private static final String KEY_PREF = "my_static_page_entry_key";

    public MyStaticCardController(Context context) {
        super(context, KEY_PREF);
    }

    @Override
    public int getAvailabilityStatus() {
        // 如果你需要这个卡片总是可用，返回 AVAILABLE
        // 如果有特定条件，可以在这里添加判断逻辑
        return AVAILABLE;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        
        // 1. 获取你在XML中定义的LayoutPreference
        LayoutPreference layoutPreference = screen.findPreference(getPreferenceKey());
        if (layoutPreference == null) {
            return;
        }
        TextView liberdroidText = layoutPreference.findViewById(R.id.text_liberdroid);
        // 2. 找到自定义布局中的TextViews
        TextView versionName = layoutPreference.findViewById(R.id.text_version);
        TextView deviceName = layoutPreference.findViewById(R.id.text_device_name); // 你需要在你的layout中为它添加ID
        TextView processorName = layoutPreference.findViewById(R.id.text_processor); // 你需要在你的layout中为它添加ID
        setLDFocusColor(liberdroidText, versionName, deviceName, processorName);
        
    }

    public void setLDFocusColor(TextView liberdroidText, TextView versionName, TextView deviceName, TextView processorName) {

        /*
        // 3. 更新TextView的内容
        if (versionName != null) {
            // 获取你想要显示的版本信息
            String versionInfo = mContext.getString(R.string.firmware_version); 
            versionName.setText(versionInfo);
        }*/
        
        /*
        if (deviceName != null) {
            // 获取并显示设备名称
            String deviceModel = Build.MODEL;
            deviceName.setText(deviceModel);
        }*/
        
        /*if (processorName != null) {
            // 获取并显示处理器信息
            String processorInfo = getProcessorInfo(); // 这是一个你需要自己实现的方法
            processorName.setText(processorInfo);
        }*/

        /*
        // 4. (可选) 为卡片添加点击事件，以跳转到新页面
        View cardView = layoutPreference.findViewById(R.id.card_liberdroid);
        if (cardView != null) {
            cardView.setOnClickListener(v -> {
                // 定义点击后跳转的Intent
                Intent intent = new Intent();
                intent.setClass(mContext, MyNewActivity.class); // 替换成你想要跳转的Activity
                mContext.startActivity(intent);
            });
        }*/
        
        
        String text = "LibreDroid";
        SpannableString spannable = new SpannableString(text);

        int focusColor;
        int commonColor; 

        if (isNightModeEnabled()){
            focusColor = mContext.getColor(R.color.text_focus_dark);
            commonColor = mContext.getColor(R.color.text_common_dark);
        } else {
            focusColor = mContext.getColor(R.color.text_focus_light);
            commonColor = mContext.getColor(R.color.text_common_light);
        }
        

        // 给特定字母设置颜色（L和D）
        spannable.setSpan(new ForegroundColorSpan(focusColor), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(commonColor), 1, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(focusColor), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(commonColor), 6, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 应用到TextView
        liberdroidText.setText(spannable);
        versionName.setTextColor(focusColor);
    }

    public boolean isNightModeEnabled() {
        int nightModeFlags = mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        //setLDFocusColor();
    }

    /**
     * 这里是一个示例方法，用来获取处理器信息。
     * 你需要根据自己的需求来修改它。
     */
    /*private String getProcessorInfo() {
        // 比如，从系统属性中获取
        return System.getProperty("os.arch", "Unknown Processor");
    }*/
}
