package zkch.com.exerdemo.cniaow.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerAppDetailComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.cniaow.mvp.module.AppDetailModule;
import zkch.com.exerdemo.cniaow.mvp.module.AppModelModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppDetailPresenter;

@SuppressLint("ValidFragment")
public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView viewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.txt_apk_size)
    TextView txtApkSize;
    @BindView(R.id.txt_publisher)
    TextView txtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView txtPublisher2;
    @BindView(R.id.recyleView)
    RecyclerView recyView;
    @BindView(R.id.recyleView_relate)
    RecyclerView recyViewRelate;

    private LayoutInflater mInflater;
    private int mAppId;

    public AppDetailFragment(int mAppId) {
        this.mAppId = mAppId;
    }

    @Override
    protected void init() {
        mInflater = LayoutInflater.from(getActivity());
        mPresenter.getAppDetail(mAppId);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder().appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .appModelModule(new AppModelModule()).build().inject(this);
    }

    @Override
    public void showAppDetail(AppInfo appInfo) {

    }

}
