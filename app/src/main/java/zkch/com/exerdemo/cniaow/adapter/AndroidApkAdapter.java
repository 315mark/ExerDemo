package zkch.com.exerdemo.cniaow.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zkch.com.exerdemo.common.rx.RxSchedulers;
import zkch.com.exerdemo.common.utils.AppUtils;
import zkch.com.exerdemo.common.utils.PackageUtils;
import zkch.com.exerdemo.widget.DownloadProgressButton;

public class AndroidApkAdapter extends BaseQuickAdapter<AndroidApk, BaseViewHolder> {

    public static final int FLAG_APK = 0;
    public static final int FLAG_APP = 1;
    private int flag;

    public AndroidApkAdapter(int flag) {
        super(R.layout.template_apk);
        this.flag = flag;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void convert(BaseViewHolder helper, AndroidApk item) {
        helper.setText(R.id.txt_app_name, item.getAppName())
                .setImageDrawable(R.id.imgView_app, item.getDrawable())
                .addOnClickListener(R.id.btn_action);
        final DownloadProgressButton btn = helper.getView(R.id.btn_action);
        final TextView Status = helper.getView(R.id.txt_status);
        if (flag == FLAG_APK) {
            btn.setTag(R.id.tag_package_name, item.getPackageName());
            btn.setText("删除");

            RxView.clicks(btn).subscribe(o -> {
                if (btn.getTag(R.id.tag_package_name).toString().equals(item.getPackageName())) {
                    Object tag = btn.getTag();
                    if (tag == null) {
                        PackageUtils.install(mContext, item.getApkPath());
                    } else {
                        boolean isInstall = (boolean) tag;
                        if (isInstall) {
                            deleteApk(item);
                        } else {
                            PackageUtils.install(mContext, item.getApkPath());
                        }
                    }
                }
            });
            isInstalled(mContext, item.getPackageName()).subscribe(aBoolean -> {
                btn.setTag(aBoolean);
                if (aBoolean) {
                    Status.setText("已安装");
                    btn.setText("删除");
                } else {
                    Status.setText("等待安装");
                    btn.setText("安装");
                }
            });

        } else if (flag == FLAG_APP) {
            btn.setText("卸载");
            RxView.clicks(btn).subscribe(o ->
                    AppUtils.uninstallApk(mContext, item.getPackageName()));
            Status.setText("v" + item.getAppVersionName() + "" + (item.isSystem() ? "内置" : "第三方"));
        }
    }

    //删除下载记录  删除文件
    private void deleteApk(AndroidApk item) {
//        FileUtils.deleteFile(item.getApkPath());
    }

    public Observable<Boolean> isInstalled(Context context, String packname) {
        return Observable.create((ObservableOnSubscribe<Boolean>)
                emitter -> emitter.onNext(AppUtils.isInstalled(context, packname)))
                .compose(RxSchedulers.io_mian());
    }

}
