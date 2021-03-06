package zkch.com.exerdemo.cniaow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import zkch.com.exerdemo.AppApplication;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.Banner;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.ui.AppDetailActivity;
import zkch.com.exerdemo.cniaow.ui.SubjectActivity;
import zkch.com.exerdemo.widget.BannerLayout;
import zlc.season.rxdownload2.RxDownload;

import static zkch.com.exerdemo.common.constant.Constant.APPINFO;

/**
 * 多类型RecyleView 复用
 */
public class IndexMultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;

    private final LayoutInflater mLayoutInflater;

    private IndexBean mIndexBean;
    private Context mContext;
    private List<AppInfo> appInfos;
    private RxDownload rxDownload;

    public IndexMultAdapter(Context mContext, RxDownload download) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.rxDownload = download;
    }

    /**
     * 获取Item的类型
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_BANNER;

            case 1:
                return TYPE_ICON;

            case 2:
                return TYPE_APPS;

            case 3:
                return TYPE_GAMES;

            default:
                return TYPE_BANNER;
        }
    }

    /**
     * 设置数据  可以根据开发情况设置全局刷新或者局部Item刷新
     */
    public void setData(IndexBean indexBean) {
        mIndexBean = indexBean;
        notifyDataSetChanged();
    }

    /**
     * 不同的item 添加不同的ViewHolder
     *
     * @param parent   参数
     * @param viewType 类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_BANNER:
                return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner, parent, false));

            case TYPE_ICON:
                return new NavIconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));

            case TYPE_APPS:
                return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_APPS);

            case TYPE_GAMES:
                return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_GAMES);

            default:
                return null;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (position) {
            case 0:   //banner 轮播Item
                BannerViewHolder bannerHolder = (BannerViewHolder) holder;
                List<Banner> banners = mIndexBean.getBanners();
                ArrayList<String> urls = new ArrayList<>(banners.size());
                for (Banner banner : banners) {
                    urls.add(banner.getThumbnail());
                }
                bannerHolder.banner.setViewUrls(urls);
                bannerHolder.banner.setOnBannerItemClickListener(position1 -> Timber.i("onItemClick: %s", position1));
                break;

            case 1:    //热门主题
                NavIconViewHolder iconViewHolder = (NavIconViewHolder) holder;
                iconViewHolder.layoutHotApps.setOnClickListener(this);
                iconViewHolder.layoutHotGame.setOnClickListener(this);
                iconViewHolder.layoutHotSubject.setOnClickListener(this);
                break;
            case 2:
            case 3:    //热门应用 //热门游戏
                AppViewHolder appViewHolder = (AppViewHolder) holder;
                AppInfoAdapter appInfoAdapter = AppInfoAdapter.builder()
                        .showBrief(true)
                        .showPosition(false)
                        .showCategoryName(false)
                        .rxDownload(rxDownload)
                        .build();
                if (appViewHolder.type == TYPE_APPS) {
                    appViewHolder.text.setText("热门应用");
                    appViewHolder.text.setTextColor(mContext.getResources().getColor(R.color.md_pink_A700));
                    appInfos = mIndexBean.getRecommendApps();
                } else {
                    appViewHolder.text.setText("热门游戏");
                    appViewHolder.text.setTextColor(mContext.getResources().getColor(R.color.md_red_A700));
                    appInfos = mIndexBean.getRecommendGames();
                }
                appInfoAdapter.addData(appInfos);
                // TODO: set inner adapter
                appViewHolder.recyleView.setAdapter(appInfoAdapter);
                appViewHolder.recyleView.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        AppApplication.getInstance().setmView(view);
                        Intent intent = new Intent(mContext, AppDetailActivity.class);
                        AppInfo appInfo = (AppInfo) adapter.getData().get(position);
                        intent.putExtra(APPINFO, appInfo);
                        mContext.startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_hot_apps:
                break;

            case R.id.layout_hot_game:
                break;

            case R.id.layout_hot_subject:
                mContext.startActivity(new Intent(mContext, SubjectActivity.class));
                break;
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        BannerLayout banner;

        BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            banner.setImageLoader(new ImgLoader());
        }
    }

    //加载图片
    class ImgLoader implements BannerLayout.ImageLoader {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(mContext).load(path).into(imageView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.recyleView)
        RecyclerView recyleView;

        private final int type;

        AppViewHolder(View view, int typeApps) {
            super(view);
            ButterKnife.bind(this, view);
            this.type = typeApps;
            initRecyclerView();
        }

        private void initRecyclerView() {
            recyleView.setLayoutManager(new LinearLayoutManager(mContext) {
                @Override
                public boolean canScrollVertically() { //设置不能垂直滚动
                    return false;
                }
            });
            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            recyleView.addItemDecoration(itemDecoration);

        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_hot_apps)
        LinearLayout layoutHotApps;
        @BindView(R.id.layout_hot_game)
        LinearLayout layoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout layoutHotSubject;

        NavIconViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
