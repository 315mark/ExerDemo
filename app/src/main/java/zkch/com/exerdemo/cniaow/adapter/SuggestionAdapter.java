package zkch.com.exerdemo.cniaow.adapter;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import zkch.com.exerdemo.R;

/**
 * BaseQuickAdapter<T,BaseViewHolder> T 是泛型 可以是实体类
 */
public class SuggestionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SuggestionAdapter() {
        super(R.layout.suggest_item); //super中可带数据源
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //可链式调用赋值  如果不在adapter中绑定，点击事件无法生效
        helper.setText(R.id.txt_suggestion, item)
                .setImageDrawable(R.id.icon_suggestion
                        , new IconicsDrawable(mContext, Ionicons.Icon.ion_ios_search)
                                .color(ContextCompat.getColor(mContext, R.color.white))
                                .sizeDp(16))
                //.addOnLongClickListener()
                //.setImageResource()
                .addOnClickListener(R.id.icon_suggestion) //给图标设置点击事件
                .addOnClickListener(R.id.txt_suggestion);

        //获取当前条目position
        //int position = helper.getLayoutPosition();
        // 多个Item子控件点击事件
        // ui 界面判断ID来判定是否是我要的控件
    }
}
