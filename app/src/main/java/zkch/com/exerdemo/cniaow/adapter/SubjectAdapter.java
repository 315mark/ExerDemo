package zkch.com.exerdemo.cniaow.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.common.constant.Constant;

public class SubjectAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {
    public SubjectAdapter() {
        super(R.layout.template_subject_imageview);
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {
        String url = Constant.BASE_IMG_URL + item.getMticon();
        Glide.with(mContext).load(url).into((ImageView) helper.getView(R.id.imageview));
    }
}
