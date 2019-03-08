package zkch.com.exerdemo.cniaow.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.model.AppInfoModel;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ErrorHandlerObserver;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public static final int TOP_LIST = 1;
    public static final int GAME = 2;
    public static final int CATEGORY = 3;

    // TODO: 精品
    public static final int FEATURED = 0;
    // TODO: 排行
    public static final int TOPLIST = 1;
    // TODO: 新品
    public static final int NEWLIST = 2;

    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }

    public void requestData(int type, int page) {
        request(type, page, 0, 0);
    }

    public void requestCategoryApps(int categoryId, int page, int flagType) {
        request(CATEGORY, page, 0, 0);
    }

    private void request(int type, int page, int categoryId, int flagType) {
        Observer observer;
        // 第一页需要进度页面
        if (page == 0) {
            observer = new ProgressObserver<PageBean<AppInfo>>(mContext, mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        } else {
            //加载更多
            observer = new ErrorHandlerObserver<PageBean<AppInfo>>(mContext) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }

        getObervable(type, page, categoryId, flagType)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(observer);
    }

    /**
     *
     */
    private Observable getObervable(int type, int page, int categoryId, int flagType) {
        switch (type) {
            case TOP_LIST:
                return mModel.toList(page);

            case GAME:
                return mModel.getGames(page);

            case CATEGORY:
                if (flagType == FEATURED) {
                    return mModel.getFeaturedAppsByCategory(categoryId, page);
                } else if (flagType == TOPLIST) {
                    return mModel.getTopListAppsByCategory(categoryId, page);
                } else if (flagType == NEWLIST) {
                    return mModel.getNewListAppsByCategory(categoryId, page);
                }

            default:
                return Observable.empty();
        }
    }


}
