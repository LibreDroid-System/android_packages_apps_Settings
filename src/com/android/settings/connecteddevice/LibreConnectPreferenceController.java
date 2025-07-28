package com.android.settings.connecteddevice;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;

public class LibreConnectPreferenceController extends BasePreferenceController implements
        Preference.OnPreferenceClickListener, LifecycleObserver {

    private static final String TAG = "LibreConnectController";
    private static final String TARGET_PACKAGE = "org.kde.kdeconnect_tp";  // 目标应用包名
    private Preference mPreference;

    public LibreConnectPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        // 控制是否显示该条目（始终显示）
        return AVAILABLE;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        mPreference = screen.findPreference(getPreferenceKey());
        if (mPreference != null) {
            mPreference.setOnPreferenceClickListener(this);
        }
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // 点击时跳转应用
        Intent launchIntent = mContext.getPackageManager()
                .getLaunchIntentForPackage(TARGET_PACKAGE);
        if (launchIntent != null) {
            mContext.startActivity(launchIntent);
            return true;
        } else {
            Log.e(TAG, "Target app not installed: " + TARGET_PACKAGE);
            // 可选：显示Toast提示应用未安装
            // Toast.makeText(mContext, R.string.app_not_installed, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
