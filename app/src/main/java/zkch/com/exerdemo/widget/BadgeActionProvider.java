package zkch.com.exerdemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zkch.com.exerdemo.R;

/**
 * 自定义Toolbar menu 小红点 类似微信信息收到信息数
 * 详解可参考CSDN 严振杰的博客
 */
public class BadgeActionProvider extends ActionProvider {

    private ImageView mIcon;
    private TextView mTextView;

    private View.OnClickListener mListener;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public BadgeActionProvider(Context context) {
        super(context);
    }

    /**
     * Factory method for creating new action views.
     *
     * @return A new action view.
     */
    @Override
    public View onCreateActionView() {
        //此处读取support下Toolbar/ActionBar的高度，让这个Menu高和宽和系统的menu达到一致。
        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_badge_provider, null, false);
        view.setLayoutParams(params);
        mIcon = view.findViewById(R.id.img_icon);
        mTextView = view.findViewById(R.id.txt_badge);

        view.setOnClickListener(new BadgeMenuClickListener());
        return view;
    }


    // 设置图标。
    public void setIcon(@DrawableRes int icon) {
        mIcon.setImageResource(icon);
    }

    public void setIcon(Drawable drawable) {
        mIcon.setImageDrawable(drawable);
    }


    // 设置显示的数字。
    public void setBadge(int i) {
        mTextView.setText(Integer.toString(i));
    }

    // 设置文字。
    public void setTextInt(@StringRes int i) {
        mTextView.setText(i);
    }

    // 设置显示的文字。
    public void setText(CharSequence i) {
        showBadge();
        mTextView.setText(i);
    }

    public int getBadgeNum() {
        try {
            return Integer.parseInt(mTextView.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public void hideBadge() {
        mTextView.setVisibility(View.GONE);
    }

    public void showBadge() {
        mTextView.setVisibility(View.VISIBLE);
    }


    public void setOnClickListener(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    private class BadgeMenuClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onClick(v);
            }
        }
    }
}
