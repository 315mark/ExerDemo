package zkch.com.exerdemo.cniaow.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import zkch.com.exerdemo.AppApplication;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.adapter.SearchHistoryAdapter;
import zkch.com.exerdemo.cniaow.adapter.SuggestionAdapter;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.SearchResult;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerSearchComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.SearchContract;
import zkch.com.exerdemo.cniaow.mvp.module.SearchModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.SearchPresenter;
import zkch.com.exerdemo.common.rx.RxSchedulers;
import zkch.com.exerdemo.widget.DividerItemDecoration;
import zkch.com.exerdemo.widget.SpaceItemDecoration;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.SearchView {

    @BindView(R.id.searchText)
    EditText searchText;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.search_bar)
    RelativeLayout searchBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_clean)
    ImageView ImgClean;
    @BindView(R.id.recyleView_history)
    RecyclerView recyleViewHistory;
    @BindView(R.id.history)
    LinearLayout history;
    @BindView(R.id.recycler_view_suggestion)
    RecyclerView recyclerViewSuggestion;
    @BindView(R.id.recycler_view_result)
    RecyclerView recyclerViewResult;

    //Adapter适配器
    private SearchHistoryAdapter mHistoryAdapter;
    private SuggestionAdapter mSuggestionAdapter;
    private AppInfoAdapter mAppAdapter;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        mApp = (AppApplication) getApplication();
        mPresenter.showHistory();
        initView();
        setupSearchView();
        initSearchResultRecyView();
        setupSuggestionRecyView();
    }

    private void setupSuggestionRecyView() {
        mSuggestionAdapter = new SuggestionAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSuggestion.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerViewSuggestion.addItemDecoration(decoration);
        recyclerViewSuggestion.setAdapter(mSuggestionAdapter);
        recyclerViewSuggestion.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mSuggestionAdapter.getItem(position));
            }
        });

        //对item子控件监听事件进行操作
        mSuggestionAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.txt_suggestion) {
            }
        });
    }

    private void initSearchResultRecyView() {
        //TODO  Adapter
//        mAppAdapter = AppInfoAdapter.builder().showBrief(false)
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewResult.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerViewResult.addItemDecoration(itemDecoration);
        recyclerViewResult.setAdapter(mAppAdapter);
        recyclerViewResult.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppInfo info = mAppAdapter.getItem(position);
                mApp.setmView(view);
                Intent intent = new Intent(SearchActivity.this, AppDetailActivity.class);
                intent.putExtra("appinfo", info);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("CheckResult")
    private void setupSearchView() {
        RxView.clicks(clear).subscribe(o -> {
            searchText.setText("");
            history.setVisibility(View.VISIBLE);
            recyclerViewSuggestion.setVisibility(View.GONE);
            recyclerViewResult.setVisibility(View.GONE);
        });

        RxTextView.editorActions(searchText).subscribe(integer ->
                search(searchText.getText().toString().trim()));

        addDisposable(RxTextView.textChanges(searchText)
                .debounce(400, TimeUnit.MILLISECONDS).compose(RxSchedulers.io_mian())
                .filter(charSequence -> charSequence.toString().trim().length() > 0)
                .subscribe(charSequence -> {
                    if (charSequence.length() > 0) {
                        ImgClean.setVisibility(View.VISIBLE);
                    } else {
                        ImgClean.setVisibility(View.GONE);
                    }
                    mPresenter.getSuggestions(charSequence.toString().trim());
                })
        );
    }

    private void search(String keyword) {
        mPresenter.search(keyword);
    }

    private void initView() {

        mToolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .color(ContextCompat.getColor(this, R.color.white))
                .sizeDp(16)
        );

        mToolbar.setNavigationOnClickListener(v -> finish());

        clear.setImageDrawable(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_close_empty)
                .color(ContextCompat.getColor(this, R.color.white))
                .sizeDp(16)
        );

        ImgClean.setImageDrawable(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_trash_outline)
                .color(ContextCompat.getColor(this, R.color.md_grey_600))
                .sizeDp(16)
        );
    }

    /**
     * 设置Activity Component
     *
     * @param appComponent
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSearchComponent.builder().appComponent(appComponent)
                .searchModule(new SearchModule(this)).build().inject(this);
    }

    @Override
    public void showSearchHistory(List<String> list) {
        initHistoryRecyView(list);
        history.setVisibility(View.VISIBLE);
        recyclerViewSuggestion.setVisibility(View.GONE);
        recyclerViewResult.setVisibility(View.GONE);
    }


    @Override
    public void showSuggestions(List<String> list) {
        mSuggestionAdapter.setNewData(list);
        recyclerViewSuggestion.setVisibility(View.VISIBLE);
        history.setVisibility(View.GONE);
        recyclerViewResult.setVisibility(View.GONE);
    }

    @Override
    public void showSearchResult(SearchResult result) {
        mAppAdapter.setNewData(result.getListApp());
        recyclerViewSuggestion.setVisibility(View.GONE);
        history.setVisibility(View.GONE);
        recyclerViewResult.setVisibility(View.VISIBLE);
    }

    //初始化
    private void initHistoryRecyView(List<String> list) {
        mHistoryAdapter = new SearchHistoryAdapter();
        mHistoryAdapter.addData(list);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 5);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(10);
        recyleViewHistory.addItemDecoration(itemDecoration);
        recyleViewHistory.setLayoutManager(manager);
        recyleViewHistory.setAdapter(mHistoryAdapter);

        recyleViewHistory.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                search(mHistoryAdapter.getItem(position));
            }
        });

        mHistoryAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            // 多控件监听事件 判断id
            if (view.getId() == R.id.btn) {
                Log.i("tag", "点击了第" + position + "条条目的 图片");
            }
        });
    }


}
