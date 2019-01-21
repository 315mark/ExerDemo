package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.cniaow.mvp.presenter.AppInfoPresenter;

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements
        AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyleView)
    RecyclerView recyleView;

    AppInfoAdapter mAdapter;

    protected int page = 0;

    abstract int type();

    protected abstract AppInfoAdapter buildAdater();

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void init() {
        mPresenter.requestData(type(), page);
        initRecyView();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(), page);
    }

    protected void initRecyView() {
        //设置布局管理器
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyleView.setItemAnimator(new DefaultItemAnimator());
        recyleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = buildAdater();
        mAdapter.setOnLoadMoreListener(this, recyleView);
        recyleView.setAdapter(mAdapter);
        recyleView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //存储视图
                mApplication.setmView(view);
                /*Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                AppInfo appInfo = mAdapter.getItem(position);
                intent.putExtra("appinfo", appInfo);
                startActivity(intent);*/
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
