package zkch.com.exerdemo.cniaow.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.widget.DownloadButtonConntroller;
import zkch.com.exerdemo.widget.DownloadProgressButton;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownLoadingAdapter extends BaseQuickAdapter<DownloadRecord, BaseViewHolder> {

    private DownloadButtonConntroller mDownloadController;

    public DownLoadingAdapter(RxDownload rxwnload) {
        super(R.layout.template_app_downloading);
        mDownloadController = new DownloadButtonConntroller(rxwnload);
    }


    @Override
    protected void convert(BaseViewHolder helper, DownloadRecord item) {
        AppInfo appInfo = mDownloadController.downloadRecor2AppInfo(item);
        ImageView view = helper.getView(R.id.img_app_icon);
        Glide.with(mContext).load(Constant.BASE_IMG_URL + appInfo.getIcon()).into(view);
        helper.setText(R.id.txt_app_name, appInfo.getDisplayName());

        helper.addOnClickListener(R.id.btn_download);
        View viewBtn = helper.getView(R.id.btn_download);

        if (viewBtn instanceof DownloadProgressButton) {
            DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
            mDownloadController.handClick(btn, item);
        }

    }
}
