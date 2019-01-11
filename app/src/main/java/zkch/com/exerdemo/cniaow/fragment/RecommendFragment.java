package zkch.com.exerdemo.cniaow.fragment;

import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.mvp.component.AppComponent;
import zkch.com.exerdemo.mvp.presenter.RecommendPresenter;
import zkch.com.exerdemo.mvp.contract.AppInfoContract;

/**
 * 推荐页面   多类型item 可从View层下手
 */
public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.RecyView{

    @BindView(R.id.recyleView)
    RecyclerView recyleView;

    @Inject
    RecommendPresenter presenter;



    @Override
    protected void init() {

    }


    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {

    }


    @Override
    public void showResult(IndexBean indexBean) {

    }

    @Override
    public void showNodata() {

    }
}
