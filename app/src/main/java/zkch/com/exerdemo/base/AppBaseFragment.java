package zkch.com.exerdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
/**
 * 应用级BaseFragment
 */

/*
public abstract class AppBaseFragment extends BaseFragment {

    protected StatusLayoutManager statusLayoutManager;
    private Unbinder unbinder;
    private FrameLayout viewContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(null, container, false);
       // viewContent = (FrameLayout) view.findViewById(R.id.view_content);

        initStatusLayout();
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    */
/**
     * 获取布局layout
     *
     * @return
     *//*

    protected abstract int getViewLayout();


    */
/**
     * 初始化多状态布局
     *//*

    private void initStatusLayout() {
        statusLayoutManager = StatusLayoutManager.newBuilder(getActivity())
                .contentView(getViewLayout())
                .emptyDataView(R.layout.layout_status_emptydata)
                .errorView(R.layout.layout_status_error)
                .loadingView(R.layout.layout_status_loading)
                .netWorkErrorView(R.layout.layout_status_networkerror)
                .retryViewId(R.id.button_retry)
                .onShowHideViewListener(new OnShowHideViewListener() {
                    @Override
                    public void onShowView(View view, int id) {
                    }

                    @Override
                    public void onHideView(View view, int id) {
                    }
                }).onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {

                    }
                }).build();

        //将多状态布局添加到内容布局中
        viewContent.addView(statusLayoutManager.getRootLayout(), 0);
        statusLayoutManager.showContent();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();//View初始化
        loadData();//数据加载

    }

    */
/**
     * View初始化
     *//*

    protected abstract void initView();

    */
/**
     * 数据加载
     *//*

    protected abstract void loadData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
    }
}
*/
