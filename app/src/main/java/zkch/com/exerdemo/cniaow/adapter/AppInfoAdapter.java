package zkch.com.exerdemo.cniaow.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.widget.DownloadButtonConntroller;
import zkch.com.exerdemo.widget.DownloadProgressButton;
import zlc.season.rxdownload2.RxDownload;

import static zkch.com.exerdemo.common.constant.Constant.BASE_IMG_URL;

/**
 * 采用Builder 构造者模式 复用item
 */
public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {
    private Builder mBuilder;

    private DownloadButtonConntroller mController;

    private AppInfoAdapter(Builder builder) {
        super(builder.layoutId);
        this.mBuilder = builder;
        //  缺少下载控制
        mController = new DownloadButtonConntroller(builder.mRxDownload);

        openLoadAnimation();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {

        Glide.with(mContext).load(BASE_IMG_URL + item.getIcon()).into((ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName())
                .setText(R.id.txt_brief, item.getBriefShow())
                .setVisible(R.id.txt_position, mBuilder.isShowPosition)
                .addOnClickListener(R.id.btn_download);

        View viewBtn = helper.getView(R.id.btn_download);

        if (viewBtn instanceof DownloadProgressButton) {
            DownloadProgressButton btn = (DownloadProgressButton) viewBtn;
            mController.handClick(btn, item);
        }

        TextView textViewBrief = helper.getView(R.id.txt_brief);
        if (mBuilder.isUpdateStatus) {
            ExpandableTextView viewChangeLog = helper.getView(R.id.view_changelog);
            viewChangeLog.setText(item.getChangeLog());
            if (textViewBrief != null) {
                textViewBrief.setVisibility(View.VISIBLE);
                textViewBrief.setText("v" + item.getVersionName() + "  " + (item.getApkSize() / 1014 / 1024) + "Mb");
            }

        } else {

            TextView txtViewPosition = helper.getView(R.id.txt_position);
            if (txtViewPosition != null) {
                txtViewPosition.setVisibility(mBuilder.isShowPosition ? View.VISIBLE : View.GONE);
                txtViewPosition.setText((item.getPosition() + 1) + " .");
            }

            TextView textViewCategoryName = helper.getView(R.id.txt_category);
            if (textViewCategoryName != null) {
                textViewCategoryName.setVisibility(mBuilder.isShowCategoryName ? View.VISIBLE : View.GONE);
                textViewCategoryName.setText(item.getLevel1CategoryName());
            }

            if (textViewBrief != null) {
                textViewBrief.setVisibility(mBuilder.isShowBrief ? View.VISIBLE : View.GONE);
                textViewBrief.setText(item.getBriefShow());
            }
            TextView textViewSize = helper.getView(R.id.txt_apk_size);
            if (textViewSize != null) {
                textViewSize.setText((item.getApkSize() / 1014 / 1024) + "Mb");
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;

        private int layoutId = R.layout.template_appinfo;
        private boolean isUpdateStatus;

        private RxDownload mRxDownload;

        public Builder showBrief(boolean b) {
            isShowBrief = b;
            return this;
        }

        public Builder showCategoryName(boolean b) {
            this.isShowCategoryName = b;
            return this;
        }

        public Builder showPosition(boolean b) {
            this.isShowPosition = b;
            return this;
        }

        public AppInfoAdapter build() {
            return new AppInfoAdapter(this);
        }

        public Builder layout(int resId) {
            this.layoutId = resId;
            return this;
        }

        public Builder rxDownload(RxDownload rxDownload) {
            this.mRxDownload = rxDownload;
            return this;
        }

        public Builder updateStatus(boolean b) {
            this.isUpdateStatus = b;
            return this;
        }
    }
}
