package zkch.com.exerdemo.cniaow.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerAppDetailComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.cniaow.mvp.module.AppDetailModule;
import zkch.com.exerdemo.cniaow.mvp.module.AppModelModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppDetailPresenter;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.util.DateUtils;

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
    private AppInfoAdapter mAdapter;


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
        showScreenshot(appInfo.getScreenshot());
        viewIntroduction.setText(appInfo.getIntroduction()); //介绍
        txtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        txtApkSize.setText((appInfo.getApkSize() / 1024 / 1024) + "Mb");
        txtVersion.setText(appInfo.getVersionName());
        txtPublisher.setText(appInfo.getPublisherName());
        txtPublisher2.setText(appInfo.getPublisherName());
        AppInfoAdapter.builder().layout(R.layout.template_appinfo2).build();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyView.setLayoutManager(manager);

        mAdapter.addData(appInfo.getSameDevAppInfoList());
        recyView.setAdapter(mAdapter);

        ////////////////////////////////
        mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2).build();
        recyViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter.addData(appInfo.getRelateAppInfoList());
        recyViewRelate.setAdapter(mAdapter);
    }

    private void showScreenshot(String screenshot) {
        List<String> urls = Arrays.asList(screenshot.split(","));
        for (String url : urls) {
            ImageView view = (ImageView) mInflater.inflate(R.layout.template_imageview, viewGallery, false);
            Glide.with(this).load(Constant.BASE_IMG_URL + url).into(view);
            viewGallery.addView(view);
        }
    }
}
