package zkch.com.exerdemo.cniaow.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.common.rx.RxSchedulers;
import zkch.com.exerdemo.common.utils.DataCleanManager;
import zkch.com.exerdemo.util.ToastUtils;

//系统自带偏好设置的基类 具体使用百度
public class SettingFragment extends PreferenceFragment {
    private static final String MISTAKE_TOUCH_MODE_KEY = "key_apk_download_dir";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res目录下新建xml目录 新建xml文件
        addPreferencesFromResource(R.xml.preferences);

        initData();
    }

    private void initData() {
        initClearCachePref();

        initDownLoadDirPref();
    }

    private void initDownLoadDirPref() {
        final Preference preference = getPreferenceManager().findPreference(MISTAKE_TOUCH_MODE_KEY);
        final String dir = ACache.get(getActivity()).getAsString(Constant.APK_DOWNLOAD_DIR);
        preference.setSummary(dir);
        preference.setOnPreferenceClickListener(pref -> {
            dirChoose(preference, dir);
            return false;
        });
    }

    //文件选择器
    private void dirChoose(Preference preference, String dir) {
    }

    @SuppressLint("CheckResult")
    private void initClearCachePref() {
        final Preference cache = getPreferenceManager().findPreference("key_clear_cache");
        try {
            cache.setSummary(DataCleanManager.getCacheSize(getActivity().getCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cache.setOnPreferenceClickListener(preference -> {
            clearCache().subscribe(o -> {
                ToastUtils.showShort("清理成功");
                cache.setSummary(DataCleanManager.getCacheSize(getActivity().getCacheDir()));
            });
            return true;
        });
    }

    private Observable clearCache() {
        return Observable.create(emitter -> {
            DataCleanManager.cleanFiles(getActivity());
            DataCleanManager.cleanInternalCache(getActivity());
            DataCleanManager.deleteFolderFile(ACache.get(getActivity()).getAsString(Constant.APK_DOWNLOAD_DIR), false);
            emitter.onNext(1);
            emitter.onComplete();
        }).compose(RxSchedulers.io_mian());

    }


}
