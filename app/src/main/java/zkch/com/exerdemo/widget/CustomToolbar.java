package zkch.com.exerdemo.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import zkch.com.exerdemo.R;

/**
 * 自定义Toolbar
 * Created by chenweisong on 2017/9/7.
 */

public class CustomToolbar extends Toolbar {

    private View titleLeft;
    private View titleRight;
    private TextView tvLeftIcon;
    private TextView tvLeftText;
    private TextView tvTitle;
    private TextView tvRightIcon;
    private TextView tvRightText;

    public CustomToolbar(Context context) {
        this(context,null);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.wiget_custom_toolbar, this);
        titleLeft = view.findViewById(R.id.rl_title_left);
        titleRight = view.findViewById(R.id.rl_title_right);

        tvLeftIcon = (TextView) view.findViewById(R.id.tv_left_icon);

        tvLeftText = (TextView) view.findViewById(R.id.tv_left_text);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);

        tvRightIcon = (TextView) view.findViewById(R.id.tv_right_icon);
        tvRightText = (TextView) view.findViewById(R.id.tv_right_text);


        initView();//View初始化

    }

    /**
     * View初始化
     */
    private void initView() {



    }

    /**
     * 设置标题
     * @param title
     */
    public CustomToolbar setTitleText(String title) {
        if (tvTitle != null) {
            tvTitle.setVisibility(VISIBLE);
            tvTitle.setText(title);
        }
        return this;
    }


    //*************************************左边设置

    /**
     * 设置左边是否可见
     * @param visiable
     */
    public CustomToolbar setTitleLeftVisiable(boolean visiable) {
        if (titleLeft==null) {
            return this;
        }
        titleLeft.setVisibility(visiable  ? VISIBLE:INVISIBLE);
        titleLeft.setClickable(visiable);
        return this;
    }


    /**
     * 标题左边点击监听
     * @param listener
     */
    public void setTitleLeftClickListener(OnClickListener listener) {

        titleLeft.setOnClickListener(listener);
    }


    public void setTitleLeftIcon(int resIcon) {

    }


    //*************************************右边设置
    /**
     * 标题右边点击监听
     * @param listener
     */
    public void setTitleRightClickListener(OnClickListener listener) {

        titleRight.setOnClickListener(listener);
    }

    /**
     * 设置右边图标
     * @param iconChart
     */
    public CustomToolbar setTitleRightIcon(Typeface typeface,String iconChart) {
        if (tvRightIcon==null) {
            return this;
        }
        tvRightIcon.setTypeface(typeface);
        tvRightIcon.setVisibility(VISIBLE);
        tvRightIcon.setText(iconChart);
        return this;
    }



    /**
     * 设置右边图标按钮显隐
     * @param visiable
     */
    public CustomToolbar setTitleRightIconVisiable(int visiable) {
        if (tvRightIcon==null) {
            return this;
        }
        tvRightIcon.setVisibility(visiable);
        return this;
    }

    /**
     * 设置右边图标按钮点击事件
     * @param listener
     */
    public void setTitleRightIconClickListener(OnClickListener listener) {
        if (tvRightIcon==null) {
            return;
        }
        tvRightIcon.setOnClickListener(listener);
    }



     /**
     * 设置右边文本
     * @param rightText
     */
    public CustomToolbar setTitleRightText(String rightText) {
        if (tvRightText==null) {
            return this;
        }
        tvRightText.setVisibility(VISIBLE);
        tvRightText.setText(rightText);
        return this;
    }









}
