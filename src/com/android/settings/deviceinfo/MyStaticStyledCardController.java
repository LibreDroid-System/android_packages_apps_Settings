package com.android.settings.deviceinfo;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.cardview.widget.CardView;
import com.android.settings.R;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.LayoutPreference;

public class MyStaticStyledCardController extends AbstractPreferenceController {

    private final String mPreferenceKey;
    
    private TextView liberdroidText;
    private TextView versionText;
    private LinearLayout mainContainer;
    private CardView liberdroidCard;
    private CardView deviceNameCard;  // 底部设备名称卡片
    private CardView processorCard;   // 底部处理器卡片

    // 构造函数接收 Preference Key
    public MyStaticStyledCardController(Context context, String preferenceKey) {
        super(context);
        mPreferenceKey = preferenceKey;
    }

    @Override
    public boolean isAvailable() {
        return true; // 这个静态卡片总是可见
    }

    @Override
    public String getPreferenceKey() {
        return mPreferenceKey;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        final LayoutPreference layoutPreference = screen.findPreference(getPreferenceKey());
        if (layoutPreference != null) {
            /*String text = "LibreDroid";
            SpannableString spannable = new SpannableString(text);// xml text is disabled
            
            final TextView libredroidText= (TextView) layoutPreference.findViewById(R.id.text_liberdroid);
            
            final TextView liberdroidText;
            final TextView versionText;
            final LinearLayout mainContainer;
            final CardView liberdroidCard;
            final CardView deviceNameCard;  // 底部设备名称卡片
            final CardView processorCard;   // 底部处理器卡片
            */
            
            /*
            
            int colorFocus;
            int colorCommon;
            colorFocus = Color.parseColor("#FF8A7AE8");
            colorCommon = Color.parseColor("#99000000"); // 注意这个颜色是半透明黑
            
            spannable.setSpan(new ForegroundColorSpan(colorFocus), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(colorCommon), 1, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(colorFocus), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(colorCommon), 6, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            libredroidText.setText(spannable);*/
            
            liberdroidText = layoutPreference.findViewById(R.id.text_liberdroid);
            versionText = layoutPreference.findViewById(R.id.text_version);
            mainContainer = layoutPreference.findViewById(R.id.testing_info);
            liberdroidCard = layoutPreference.findViewById(R.id.card_liberdroid);
            deviceNameCard = layoutPreference.findViewById(R.id.card_device_name);  // 绑定底部卡片
            processorCard = layoutPreference.findViewById(R.id.card_processor);     // 绑定底部卡片

            // 应用主题设置（明暗模式适配）
            applyThemeSettings();
        }
    }
    
    public void applyThemeSettings() {
        // 判断当前是否为暗色模式
        
        /*int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        boolean isDarkTheme = currentNightMode == Configuration.UI_MODE_NIGHT_YES;*/
        boolean isDarkTheme = false;

        // 1. 设置文字样式（L和D字母特殊颜色）
        setLiberdroidTextStyle(isDarkTheme);

        // 2. 触发底层背景主题（灰色背景）
        mainContainer.setActivated(isDarkTheme);

        // 3. 触发顶部卡片背景（暗色模式纯黑）
        liberdroidCard.setActivated(isDarkTheme);

        // 4. 触发底部两个卡片背景（暗色模式纯黑）
        deviceNameCard.setActivated(isDarkTheme);
        processorCard.setActivated(isDarkTheme);
    }
    
    public void setLiberdroidTextStyle(boolean isDarkTheme) {
        String text = "LibreDroid";
        SpannableString spannable = new SpannableString(text);

        // 根据主题获取文字颜色（引用colors.xml资源）
        int focusColor = Color.parseColor("#FFBB86FC");
        int commonColor = Color.parseColor("#B3FFFFFF"); 

        // 给特定字母设置颜色（L和D）
        spannable.setSpan(new ForegroundColorSpan(focusColor), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(commonColor), 1, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(focusColor), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(commonColor), 6, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 应用到TextView
        liberdroidText.setText(spannable);
        versionText.setTextColor(focusColor);
    }

    // 对于纯静态卡片，updateState 通常不需要做什么，除非你要根据外部条件改变它的可见性
    // @Override
    // public void updateState(Preference preference) {
    //     super.updateState(preference);
    // }
}
