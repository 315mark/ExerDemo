package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.List;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.CategoryAdapter;
import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerCategoryComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.CategoryContract;
import zkch.com.exerdemo.cniaow.mvp.module.CategoryModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.CategoryPresenter;

//分类界面
public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.CategoryView {

    @BindView(R.id.recyleView)
    RecyclerView recyleView;
    private CategoryAdapter mAdapter;

    @Override
    protected void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    private void initRecyclerView() {
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyleView.addItemDecoration(itemDecoration);
        //设置数据源
        mAdapter = new CategoryAdapter();
        recyleView.setAdapter(mAdapter);
        recyleView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /*Intent intent = new Intent();
                intent.setClass(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY, mAdapter.getData().get(position));
                startActivity(intent);*/
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent)
                .categoryModule(new CategoryModule(this)).build();
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }

}
