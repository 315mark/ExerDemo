package zkch.com.exerdemo.cniaow.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.common.constant.Constant;

public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {

    public CategoryAdapter() {
        super(R.layout.template_category);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.text_name, item.getName());
        Glide.with(mContext).load(Constant.BASE_IMG_URL + item.getIcon()).into((ImageView) helper.getView(R.id.img_icon));
    }
}
