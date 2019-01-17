package zkch.com.exerdemo.cniaow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.bean.Banner;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.widget.BannerLayout;

/**
 * 多类型RecyleView 复用
 */
public class IndexMultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;
    private final LayoutInflater mLayoutInflater;

    private IndexBean mIndexBean;
    private Context mContext;

    public IndexMultAdapter(Context mContext) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 获取Item的类型
     *
     * @return
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
                return new BannerViewHolder(mLayoutInflater.inflate(R.layout.activity_main, parent, false));

            case TYPE_APPS:
                return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, parent, false), TYPE_APPS);

            case TYPE_GAMES:
                return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, parent, false), TYPE_GAMES);

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
                bannerHolder.banner.setOnBannerItemClickListener(position1 -> Log.i("AAA", "onItemClick: " + position1));
                break;

            case 1:    //


                break;


            case 2:    //热门应用 //热门游戏
                AppViewHolder appHolder = (AppViewHolder) holder;
                AppInfoAdapter mAdapter = AppInfoAdapter.build().showBrief(false)
                        .showCategoryName(true).showPosition(false).build();
                if (appHolder.type == TYPE_APPS) {
                    appHolder.text.setText("热门应用");
                    mAdapter.addData(mIndexBean.getRecommendApps());
                } else {
                    appHolder.text.setText("热门游戏");
                    mAdapter.addData(mIndexBean.getRecommendGames());
                }
                appHolder.recyleView.setAdapter(mAdapter);
                appHolder.recyleView.addOnItemTouchListener(new OnItemChildClickListener() {
                    @Override
                    public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });

                break;

            case 3:


                break;
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        BannerLayout banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            banner.setImageLoader(new ImgLoader());
        }
    }

    private class ImgLoader implements BannerLayout.ImageLoader {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(mContext).load(path).into(imageView);
        }
    }

    private class AppViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.recyleView)
        RecyclerView recyleView;

        private final int type;

        public AppViewHolder(View view, int typeApps) {
            super(view);
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


}
