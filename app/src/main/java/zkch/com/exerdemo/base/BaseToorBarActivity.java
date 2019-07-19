package zkch.com.exerdemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import butterknife.ButterKnife;
import zkch.com.exerdemo.R;

/**
 * "
 * 封装公用ToolBar 布局
 */
public abstract class BaseToorBarActivity extends AppCompatActivity {

    @BindView(R.id.rlCustom)
    RelativeLayout rlCustom;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rlContent)
    RelativeLayout rlContent;
    private ToolbarX mToolbarX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_baselayout);
        ButterKnife.bind(this);
        View v = getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(v);
        mToolbarX = new ToolbarX(toolbar, this);
        getToolbar().setTitle(R.string.app_name);

        toolbar.setNavigationIcon(new IconicsDrawable(this).icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16).color(getResources().getColor(R.color.md_white_1000)));
        toolbar.setNavigationOnClickListener(v1 -> finish());

        initView();
    }

    public abstract int getLayoutId();

    public abstract int initView();

    //由左往右的activity跳转动画
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.animator.anmi_in_right_left, R.animator.anmi_out_right_left);
    }

    //由右往左的activity退出动画
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.animator.anim_in_left_right, R.animator.anit_out_left_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.animator.anmi_in_right_left, R.animator.anmi_out_right_left);
    }

    public ToolbarX getToolbar() {
        if (null == mToolbarX) {
            mToolbarX = new ToolbarX(toolbar, this);
        }
        return mToolbarX;
    }
}
