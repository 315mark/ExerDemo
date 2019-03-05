package zkch.com.exerdemo.cniaow.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.fragment.SettingFragment;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResID() {
        return R.layout.template_toolbar_framelayout;
    }

    @Override
    protected void init() {
        mToolbar.setNavigationIcon(new IconicsDrawable(this).sizeDp(16)
                .color(ContextCompat.getColor(this, R.color.white))
                .icon(Ionicons.Icon.ion_ios_arrow_back)
        );
        mToolbar.setTitle("系统设置");
        mToolbar.setNavigationOnClickListener(v -> finish());

        getFragmentManager().beginTransaction().replace(R.id.content, new SettingFragment()).commit();
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
