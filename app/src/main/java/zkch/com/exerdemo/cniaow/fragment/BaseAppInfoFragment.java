package zkch.com.exerdemo.cniaow.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import javax.inject.Inject;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppInfoPresenter;
import zkch.com.exerdemo.cniaow.ui.AppDetailActivity;
import zkch.com.exerdemo.widget.DividerItemDecoration;
import zlc.season.rxdownload2.RxDownload;

import static zkch.com.exerdemo.common.constant.Constant.APPINFO;

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter>
        implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyleView)
    RecyclerView recyleView;

    @Inject
    RxDownload mRxDownload;

    private AppInfoAdapter mAdapter;

    int page = 0;

    public abstract int type();

    public abstract AppInfoAdapter buildAdater();

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void init() {
        initRecyView();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.requestData(type(), page);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(), page);
    }

    protected void initRecyView() {
        //设置布局管理器
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyleView.setItemAnimator(new DefaultItemAnimator());
        recyleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = buildAdater();

        mAdapter.setOnLoadMoreListener(this, recyleView);
        recyleView.setAdapter(mAdapter);
        recyleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //存储视图
                mApplication.setmView(view);
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                AppInfo appInfo = mAdapter.getItem(position);
                intent.putExtra(APPINFO, appInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

}
