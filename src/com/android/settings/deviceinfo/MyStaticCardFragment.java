package com.android.settings.deviceinfo;

import android.app.settings.SettingsEnums;
import android.content.Context;
import android.content.res.Configuration;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;

import java.util.ArrayList;
import java.util.List;

public class MyStaticCardFragment extends DashboardFragment {

    private static final String LOG_TAG = "MyStaticCardFragment";

    @Override
    public int getMetricsCategory() {
        // 重要: 为你的页面选择一个唯一的、相关的 Metrics Category 值。
        // 你可以查看 SettingsEnums.java 来找到一个未使用的值或定义一个新的。
        // 例如，如果你的页面是关于某些自定义设备信息:
        return SettingsEnums.SETTINGS_STORAGE_CATEGORY; // 示例，请替换为实际存在的或新定义的
    }

    @Override
    protected String getLogTag() {
        return LOG_TAG;
    }

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.my_static_card_page;
    }

    @Override
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(
            Context context, Lifecycle lifecycle) {
        final List<AbstractPreferenceController> controllers = new ArrayList<>();

        // Controller for the first styled card
        controllers.add(new MyStaticStyledCardController(context, "my_first_static_styled_card"));
        
        // Controller for the second styled card
        controllers.add(new MyStaticStyledCardController(context, "my_second_static_styled_card"));

        // Controller for the standard preference (if it needs one, e.g., for availability)
        // controllers.add(new StandardPreferenceController(context, "a_standard_preference_item"));

        return controllers;
    }
}
