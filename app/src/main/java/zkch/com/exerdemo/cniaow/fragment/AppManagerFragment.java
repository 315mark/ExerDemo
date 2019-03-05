package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerAppManagerComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.AppManagerContract;
import zkch.com.exerdemo.cniaow.mvp.module.AppManagerModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppManagerPresenter;
import zkch.com.exerdemo.common.apkparest.AndroidApk;
import zkch.com.exerdemo.widget.DividerItemDecoration;

public abstract class AppManagerFragment extends ProgressFragment<AppManagerPresenter> implements AppManagerContract.AppManagerView {

    @BindView(R.id.recyleView)
    RecyclerView recyleView;


    @Override
    protected void init() {
        initRecylerView();
    }

    private void initRecylerView() {
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyleView.addItemDecoration(decoration);
        recyleView.setAdapter(setAdapter());
    }

    protected abstract RecyclerView.Adapter setAdapter();

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        //TODO
        DaggerAppManagerComponent.builder().appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this)).build().inject(this);

    }

    @Override
    public void showApp(List<AndroidApk> apps) {

    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {

    }


}
