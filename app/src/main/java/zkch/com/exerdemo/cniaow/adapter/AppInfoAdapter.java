package zkch.com.exerdemo.cniaow.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;

/**
 * 采用Builder 构造者模式 复用item
 */
public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {
    private Builder mBuilder;
    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public AppInfoAdapter(Builder builder) {
        super(R.layout.template_appinfo);
        this.mBuilder = builder;
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        Glide.with(getRecyclerView()).load(baseImgUrl + item.getIcon()).into((ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName())
                .setText(R.id.txt_brief, item.getBriefShow());
        TextView txtViewPosition = helper.getView(R.id.txt_position);
        txtViewPosition.setVisibility(mBuilder.isShowPosition ? View.VISIBLE : View.GONE);
        txtViewPosition.setText(item.getPosition() + 1 + ". ");
        TextView txtViewCategory = helper.getView(R.id.txt_category);
        txtViewCategory.setVisibility(mBuilder.isShowCategoryName ? View.VISIBLE : View.GONE);
        txtViewCategory.setText(item.getLevel1CategoryName());
        TextView txtViewBrief = helper.getView(R.id.txt_brief);
        txtViewBrief.setVisibility(mBuilder.isShowBrief ? View.VISIBLE : View.GONE);
        txtViewBrief.setText(item.getBriefShow());

    }


    public static Builder build() {
        return new Builder();
    }

    public static class Builder {

        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;


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

    }
}
