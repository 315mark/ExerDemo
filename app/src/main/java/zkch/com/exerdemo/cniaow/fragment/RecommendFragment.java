package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.IndexMultAdapter;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerRecommendComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.cniaow.mvp.module.RecommendModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.RecommendPresenter;
import zkch.com.exerdemo.util.ToastUtils;

/**
 * 推荐页面   多类型item 可从View层下手
 */
public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.RecyView {

    @BindView(R.id.recyleView)
    RecyclerView recyleView;

    @Inject
    RecommendPresenter presenter;

    //Adapter 适配器
    private IndexMultAdapter adapter;


    @Override
    protected void init() {
        initData();
    }

    private void initData() {
        presenter.requestDatas();
    }

    @Override
    public void showResult(IndexBean indexBean) {
        initRecycleView();
        adapter.setData(indexBean);
    }

    /**
     * 添加动画
     */
    private void initRecycleView() {
        // 设置布局管理器
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyleView.setItemAnimator(new DefaultItemAnimator());
        adapter = new IndexMultAdapter(getActivity());
        recyleView.setAdapter(adapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build().inject(this);
    }


    @Override
    public void onEmptyViewClick() {
        initData();
    }

    @Override
    public void showNodata() {
        ToastUtils.showShort("没有数据");
    }
}
