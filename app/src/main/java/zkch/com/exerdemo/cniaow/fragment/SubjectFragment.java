package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.SubjectAdapter;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.common.rx.RxBus;
import zkch.com.exerdemo.widget.SpaceItemDecoration;

public class SubjectFragment extends BaseSubjectFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyleView)
    RecyclerView recyleView;

    int page = 0;

    private SubjectAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void init() {
        initRecyclerView();

        mPresenter.getSujects(page);
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyleView.setLayoutManager(manager);
        SpaceItemDecoration decoration = new SpaceItemDecoration(2);
        recyleView.addItemDecoration(decoration);

        mAdapter = new SubjectAdapter();
        mAdapter.setOnLoadMoreListener(this, recyleView);
        recyleView.setAdapter(mAdapter);

        recyleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxBus.getInstance().post(mAdapter.getItem(position));
            }
        });
    }

    @Override
    public void showSubjects(PageBean<Subject> data) {
        super.showSubjects(data);
        mAdapter.addData(data.getDatas());

        if (data.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(data.isHasMore());

    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getSujects(page);
    }

}
