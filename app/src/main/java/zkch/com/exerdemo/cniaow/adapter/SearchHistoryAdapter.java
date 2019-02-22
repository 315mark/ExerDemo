package zkch.com.exerdemo.cniaow.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import zkch.com.exerdemo.R;

public class SearchHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchHistoryAdapter() {
        super(R.layout.template_search_history); //super中可带数据源
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //可链式调用赋值  给子控件添加点击监听  UI界面实现 如果不在adapter中绑定，点击事件无法生效
        helper.setText(R.id.btn, item)
                //.addOnLongClickListener()
                //.setImageResource()
                .addOnClickListener(R.id.btn);

        //获取当前条目position
        //int position = helper.getLayoutPosition();
        // 多个Item子控件点击事件
        // ui 界面判断ID来判定是否是我要的控件
    }
}